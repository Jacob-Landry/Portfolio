using GameReview.Models.Enums;
using RevueJeu.Controller;
using System.ComponentModel.DataAnnotations.Schema;

namespace GameReview.Models
{
    public class JeuModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string NomDuJeu { get; set; }
        public virtual ICollection<EnumGenreDeJeu> GenresDuJeu { get; set; }
        public virtual ICollection<EnumCaracteristiqueDeJeu> CaracteristiquesDuJeu { get; set; }
        public virtual ICollection<EnumLangagesDeJeu> LangagesDuJeu { get; set; }
        public virtual DateTime DateDeSortie { get; set; }
        public double Prix { get; set; }
        public bool ContientDuContenuSupplementaire { get; set; }
        public virtual ICollection<EvaluationModel> ListeDEvaluations { get; set; }
        
        //join one to many
        public int CatalogueId { get; set; }
        public virtual CatalogueModel Catalogue { get; set; }
        public virtual ICollection<UtilisateurModel> Utilisateur { get; set; }
        public int StudioDeDeveloppementId { get; set; }
        public virtual StudioDeveloppementModel StudioDeDeveloppement { get; set; }
        public int EditeurId { get; set; }
        public virtual EditeurModel Editeur { get; set; }


        public JeuModel(string nomDuJeu, DateTime dateDeSortie, double prix, bool contientDuContenuSupplementaire, int catalogueId, int studioDeDeveloppementId, int editeurId)
        {
            this.NomDuJeu = nomDuJeu;
            this.GenresDuJeu = new List<EnumGenreDeJeu>();
            this.CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>();
            this.LangagesDuJeu = new List<EnumLangagesDeJeu>();
            this.DateDeSortie = dateDeSortie;
            this.Prix = prix;
            this.ContientDuContenuSupplementaire = contientDuContenuSupplementaire;
            this.ListeDEvaluations = new List<EvaluationModel>();
            this.CatalogueId = catalogueId;
            this.StudioDeDeveloppementId = studioDeDeveloppementId;
            this.EditeurId = editeurId;
        }

        public JeuModel()
        {
            this.GenresDuJeu = new List<EnumGenreDeJeu>();
            this.CaracteristiquesDuJeu = new List<EnumCaracteristiqueDeJeu>();
            this.LangagesDuJeu = new List<EnumLangagesDeJeu>();
            this.ListeDEvaluations = new List<EvaluationModel>();
        }

        public override string ToString()
        {
            string value = "";
            
            value += String.Format("NomDuJeu : {0}\n", NomDuJeu);
            value += String.Format("GenresDuJeu : \n\t[{0}]\n", String.Join(", ", this.GenresDuJeu));
            value += String.Format("CaracteristiquesDuJeu : \n\t[{0}]\n", String.Join(", ", this.CaracteristiquesDuJeu));
            value += String.Format("LangagesDuJeu : \n\t[{0}]\n", String.Join(", ", this.LangagesDuJeu));
            value += String.Format("DateDeSortie : {0}\n", DateDeSortie);
            value += String.Format("StudioDeDeveloppement : {0}\n", StudioDeDeveloppement.Nom);
            value += String.Format("Editeur : {0}\n", Editeur.Nom);
            value += String.Format("Prix : {0}\n", Prix);
            value += String.Format("ContientDuContenuSupplementaire : {0}\n", ContientDuContenuSupplementaire);
           
            return value;
            
        }
    }
}
