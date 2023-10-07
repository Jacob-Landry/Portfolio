using GameReview.Models;
using Microsoft.AspNetCore.Mvc;
using RevueJeu.Controller;
using System.Diagnostics;
using TP2.Models;

namespace TP2.Controllers
{
    public class HomeController : Controller
    {
        private TopGameContext _context = new TopGameContext();
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            string usernameVerify = HttpContext.Session.GetString("username");
            TempData["username"] = usernameVerify;
            TempData["role"] = HttpContext.Session.GetString("role");
            if (usernameVerify == null)
            {
                return RedirectToAction("Index", "Landing");
            }
            return View(_context.Catalogues.Single(x => x.NomCatalogue == "MainCatalogue"));
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

        public ActionResult Game(string gameName)
        {
            gameName = gameName == null ? "" : gameName;

            string usernameVerify = HttpContext.Session.GetString("username");
            TempData["username"] = usernameVerify;
            TempData["role"] = HttpContext.Session.GetString("role");
            if (usernameVerify == null)
            {
                return RedirectToAction("Index", "Landing");
            }

            UtilisateurModel utilisateur = _context.Utilisateurs.Single(x => x.Login == usernameVerify);
            CatalogueModel catalogue = _context.Catalogues.Single(x => x.NomCatalogue == "MainCatalogue");
            JeuModel? jeu = _context.Jeux.Single(x => x.NomDuJeu == gameName);
            Tuple<UtilisateurModel, JeuModel> modelTuple = new Tuple<UtilisateurModel, JeuModel>(utilisateur, jeu);

            if (jeu != null)
            {
                return View(modelTuple);
            }
            else
            {
                return RedirectToAction("Index", "Home");
            }
        }

        [HttpPost]
        public ActionResult ToggleFav(string gameName)
        {
            string usernameVerify = HttpContext.Session.GetString("username");
            UtilisateurModel utilisateur = _context.Utilisateurs.Single(x => x.Login == usernameVerify) ;
            CatalogueModel catalogue = _context.Catalogues.Single(x => x.NomCatalogue == "MainCatalogue");
            JeuModel? jeu = _context.Jeux.Single(x => x.NomDuJeu == gameName);

            if (jeu != null)
            {
                if (utilisateur.Favoris.Any(x => x.NomDuJeu.ToLower() == jeu.NomDuJeu.ToLower()))
                {
                    utilisateur.RetirerFavori(jeu);
                } else
                {
                    utilisateur.AjouterFavori(jeu);
                }

                _context.Utilisateurs.Update(utilisateur);
                _context.SaveChanges();
            }

            return RedirectToAction(actionName: "Game", routeValues: new {gameName = gameName});
        }

        public ActionResult ChangerDLC(string gameName)
        {
            JeuModel? jeu = _context.Jeux.Single(x => x.NomDuJeu == gameName);

            if(jeu.ContientDuContenuSupplementaire is true)
            {
                jeu.ContientDuContenuSupplementaire = false;
            }
            else
            {
                jeu.ContientDuContenuSupplementaire = true;
            }
            _context.SaveChanges();

            return RedirectToAction(actionName: "Game", routeValues: new { gameName = gameName });
        }
    }
}