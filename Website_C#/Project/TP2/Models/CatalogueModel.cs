using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json.Serialization;
using RevueJeu.Controller;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq.Expressions;

namespace GameReview.Models
{
    public class CatalogueModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string NomCatalogue { get; set; }
        public virtual ICollection<JeuModel> ListeDeJeux { get; set; }

        public CatalogueModel(string nomCatalogue, List<JeuModel> listeDeJeux)
        {
            this.NomCatalogue = nomCatalogue;
            this.ListeDeJeux = listeDeJeux;
        }

        public CatalogueModel(string nomCatalogue)
        {
            NomCatalogue = nomCatalogue;
            ListeDeJeux = new List<JeuModel>();
        }

        public CatalogueModel()
        {
            ListeDeJeux = new List<JeuModel>();
        }

        public void Ajouter(JeuModel jeu)
        {
            ListeDeJeux.Add(jeu);
        }

        public CatalogueModel CreateSubset(string search)
        {
            CatalogueModel nouvCatalogue = new CatalogueModel();

            foreach (var item in ListeDeJeux)
            {
                if (item.NomDuJeu.Contains(search) || item.StudioDeDeveloppement.Nom.Contains(search))
                {
                    nouvCatalogue.Ajouter(item);
                }
            }

            return nouvCatalogue;
        }

        public override string ToString()
        {
            string value = "";

            value += String.Format("NomCatalogue : {0}\n", NomCatalogue);
            value += String.Format("ListeDeJeux : {0}\n", "");
            int i = 0;
            foreach (JeuModel jeu in ListeDeJeux)
            {
                value += String.Format("\tListeDeJeux[{0}] : {1}\n", i, jeu.NomDuJeu);
                i++;
            }

            return value;
        }

    }
}
