using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace GameReview.Models
{
    public class StudioDeveloppementModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Nom { get; set; }
        public string Description { get; set; }
        public virtual ICollection<JeuModel> Jeux { get; set; }

        public StudioDeveloppementModel(string Nom, string Description)
        {
            this.Nom = Nom;
            this.Description = Description;
            this.Jeux = new List<JeuModel>();
        }
        public StudioDeveloppementModel(string Nom, string Description, List<JeuModel> Jeux)
        {
            this.Nom = Nom;
            this.Description = Description;
            this.Jeux = Jeux;
        }

        public StudioDeveloppementModel()
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
