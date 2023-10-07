using GameReview.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using RevueJeu.Controller;
using TP2.Models;

namespace TP2.Controllers
{
    public class SearchController : Controller
    {
        private TopGameContext _context = new TopGameContext();

        // GET: HomeController1
        public ActionResult Base()
        {
            string usernameVerify = HttpContext.Session.GetString("username");
            TempData["username"] = usernameVerify;
            TempData["role"] = HttpContext.Session.GetString("role");

            if (usernameVerify == null)
            {
                return RedirectToAction("Index", "Landing");
            }

            CatalogueModel catalogue = _context.Catalogues.Single(x => x.NomCatalogue == "MainCatalogue");
            catalogue.NomCatalogue = "All games";

            return View(catalogue);
        }

        public ActionResult Index(string t, string q)
        {
            string usernameVerify = HttpContext.Session.GetString("username");
            TempData["username"] = usernameVerify;
            TempData["role"] = HttpContext.Session.GetString("role");

            if (usernameVerify == null)
            {
                return RedirectToAction("Index", "Landing");
            }

            switch (t)
            {
                case "g":
                    return Game(q);
                case "u":
                    return User(q);
                default:
                    return Base();
            }
        }

        [HttpPost]
        public ActionResult Game(string q)
        {
            string usernameVerify = HttpContext.Session.GetString("username");
            TempData["username"] = usernameVerify;
            TempData["role"] = HttpContext.Session.GetString("role");

            if (usernameVerify == null)
            {
                return RedirectToAction("Index", "Landing");
            }

            if (q == null)
            {
                return Base();
            }

            CatalogueModel catalogue = _context.Catalogues.Single(x => x.NomCatalogue == "MainCatalogue");

            CatalogueModel subset = catalogue.CreateSubset(q);
            subset.NomCatalogue = $"Résultats pour \"{q}\"";

            return View(subset);
        }

        [HttpPost]
        public ActionResult User(string? q)
        {
            string usernameVerify = HttpContext.Session.GetString("username");
            TempData["username"] = usernameVerify;
            TempData["role"] = HttpContext.Session.GetString("role");

            if (usernameVerify == null)
            {
                return RedirectToAction("Index", "Landing");
            }

            q = q == null ? HttpContext.Session.GetString("username") : q;
            try
            {
                UtilisateurModel utilisateur = _context.Utilisateurs.Single(x => x.Login == q);
                CatalogueModel catalogue = new CatalogueModel()
                {
                    NomCatalogue = $"Favoris de {q}",
                    ListeDeJeux = utilisateur.Favoris.ToList()
                };

                return View(catalogue);

            } catch (Exception ex)
            {
                CatalogueModel catalogue = new CatalogueModel()
                {
                    NomCatalogue = $"Utilisateur inconnu"
                };

                return View(catalogue);
            }
        }
    }
}
