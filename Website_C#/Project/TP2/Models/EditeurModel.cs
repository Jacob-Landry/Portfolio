using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace GameReview.Models
{
    public class EditeurModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Nom { get; set; }
        public string Description { get; set; }
        public virtual ICollection<JeuModel> Jeux { get; set; }

        public EditeurModel(string nom, string description, List<JeuModel> jeux)
        {
            this.Nom = nom;
            this.Description = description;
            this.Jeux = jeux;
        }

        public EditeurModel(string nom, string description)
        {
            this.Nom = nom; 
            this.Description = description;
            this.Jeux = new List<JeuModel>();
        }

        public EditeurModel()
        {
            this.Jeux = new List<JeuModel>();
        }

        public override string ToString()
        {
            string value = "";

            value += String.Format("Nom : {0}\n", Nom);
            value += String.Format("Description : {0}\n", Description);

            return value;
        }
    }
}
