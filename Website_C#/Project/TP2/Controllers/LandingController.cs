using GameReview.Models;
using Microsoft.AspNetCore.Mvc;
using RevueJeu.Controller;
using System.Text.RegularExpressions;
using TP2.Models;

namespace TP2.Controllers
{
    public class LandingController : Controller
    {
        private TopGameContext _context = new TopGameContext();

        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Login()
        {
            string view = "Index";
            string controller = "Landing";

            string username = Request.Form["username"];
            string password = Request.Form["password"];

            try
            {
                UtilisateurModel utilisateur = _context.Utilisateurs.Single(x => x.Login == username);
                if (password == utilisateur.MotDePasse)
                {
                    view = "Index";
                    controller = "Home";
                    HttpContext.Session.SetString("username", username);
                    HttpContext.Session.SetString("password", password);
                    HttpContext.Session.SetString("role", utilisateur.Role.ToString());
                }
                else
                {
                    ViewBag.MessageErreur = new List<string>(){"Mot de passe invalide"};
                    return View(view, controller);

                }
            }
            catch (Exception ex)
            {
            }

            return RedirectToAction(view, controller);
        }

        [HttpPost]
        public IActionResult Register()
        {
            string view = "Index";
            string controller = "Landing";
            string usernameRegex = "^(?=.{2,20}$)[\\w\\d_]+$";
            string passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

            string username = Request.Form["username"];
            string password = Request.Form["password"];

            if (Regex.IsMatch(username, usernameRegex) && Regex.IsMatch(password, passwordRegex))
            {
                try
                {
                    UtilisateurModel user = _context.Utilisateurs.Single(x => x.Login == username);
                    if (user == null) throw new Exception();

                    ViewBag.MessageErreur = "L'utilisateur existe déjà";
                    return View(view, controller);
                }
                catch (Exception e)
                {
                    int? newId = _context.Utilisateurs.OrderByDescending(x => x.Id).FirstOrDefault().Id + 1;
                    UtilisateurModel utilisateur = new UtilisateurModel(username, password);
                    _context.Utilisateurs.Add(utilisateur);
                    _context.SaveChanges();

                    view = "Index";
                    controller = "Home";
                    HttpContext.Session.SetString("username", username);
                    HttpContext.Session.SetString("password", password);
                    HttpContext.Session.SetString("role", utilisateur.Role.ToString());
                }
            }
            else
            {
                ViewBag.MessageErreur = new List<string>(){
                "Le mot de passe n'est pas valide, voici les composances minimalement nécessaires",
                    "- 8 Caractères",
                    "- 1 Majuscule",
                    "- 1 Minuscule",
                    "- 1 Caractère spécial (@$!%*?&)",
                    "- 1 Chiffre" };

                return View(view, controller);
            }

            return RedirectToAction(view, controller);
        }

        [HttpPost]
        public IActionResult Logout()
        {
            HttpContext.Session.Clear();

            return Ok();
        }
    }

}
