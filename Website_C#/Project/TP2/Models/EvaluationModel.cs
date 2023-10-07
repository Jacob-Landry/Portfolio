using Microsoft.VisualStudio.Web.CodeGeneration.CommandLine;
using RevueJeu.Model.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Runtime.InteropServices;

namespace GameReview.Models
{
    public class EvaluationModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public virtual EnumCote Cote { get; set; }
        public string Description { get; set; }

        //join one to many
        public int AuteurId { get; set; }
        public virtual UtilisateurModel Auteur { get; set; }
        public int JeuId { get; set; }
        public virtual JeuModel Jeu { get; set; }


        public EvaluationModel(EnumCote cote, string description, int auteurId, int jeuId)
        {
            this.Cote = cote;
            this.Description = description;
            this.AuteurId = auteurId;
            this.JeuId = jeuId;
        }

        public EvaluationModel()
        {
        }

        public override bool Equals(object obj)
        {
             return obj is EvaluationModel model &&
                    EqualityComparer<JeuModel>.Default.Equals(Jeu, model.Jeu) &&
                    EqualityComparer<UtilisateurModel>.Default.Equals(Auteur, model.Auteur);
        }

        public override string ToString()
        {
            string value = "";
            
            value += String.Format("Cote : {0}\n", Cote);
            value += String.Format("Description : {0}\n", Description);
            
            return value;
        }
    }
}
