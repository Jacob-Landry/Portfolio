using GameReview.Models.Enums;
using RevueJeu.Controller;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;

namespace GameReview.Models
{
    public class UtilisateurModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Login { get; set; }
        public string MotDePasse { get; set; }
        public virtual EnumRoleUtilisateur Role { get; set; }
        public virtual ICollection<JeuModel> Favoris { get; set; }
        public virtual ICollection<EvaluationModel> Evaluations { get; set; }

        public UtilisateurModel(string login, string motDePasse, ICollection<JeuModel> favoris, ICollection<EvaluationModel> evaluations)
        {
            this.Login = login;
            this.MotDePasse = motDePasse;
            this.Favoris = favoris;
            this.Evaluations = evaluations;
        }

        public UtilisateurModel(string login, string motDePasse)
        {
            this.Login = login;
            this.MotDePasse = motDePasse;
            this.Role = EnumRoleUtilisateur.UTILISATEUR;
            this.Favoris = new List<JeuModel>();
            this.Evaluations = new List<EvaluationModel>();
        }

        public UtilisateurModel()
        {
            this.Role = EnumRoleUtilisateur.UTILISATEUR;
            this.Favoris = new HashSet<JeuModel>();
            this.Evaluations = new HashSet<EvaluationModel>();
        }

        public void AjouterFavori(JeuModel jeu)
        {
            Favoris.Add(jeu);
        }

        public void RetirerFavori(JeuModel jeu)
        {
            JeuModel ?jeuModel = Favoris.Where(x => x.NomDuJeu == jeu.NomDuJeu).FirstOrDefault();
            
            if (jeuModel != null)
            {
                Favoris.Remove(jeuModel);
            }
        }

        public void AjouterEvaluation(EvaluationModel evaluation)
        {
            Evaluations.Add(evaluation);
        }

        public override string ToString()
        {
            string value = "";
            
            IEnumerable<string> nomsJeuxFavoris = from jeu in Favoris select jeu.NomDuJeu;
            IEnumerable<string> nomsJeuxEvalues = from evaluation in Evaluations select evaluation.Jeu.NomDuJeu;

            value += String.Format("uuid : {0}\n", Id);
            value += String.Format("Login : {0}\n", Login);
            value += String.Format("MotDePasse : {0}\n", MotDePasse);
            value += String.Format("Role : {0}\n", Role);
            value += String.Format("Favoris : [{0}]\n", String.Join(", ", nomsJeuxFavoris));
            
            return value;
        }
    }
}
