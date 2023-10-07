namespace TestRevueJeu
{
    public class JeuModelTest
    {
        
        private JeuModel jeuUnderTest;

        string nomJeu;
        HashSet<EnumGenreDeJeu> listeEnumGenreDeJeu;
        HashSet<EnumCaracteristiqueDeJeu> listeEnumCaracteristiqueDeJeu;
        HashSet<EnumLangagesDeJeu> listeEnumLangagesDeJeu;
        DateTime dateDeSortie;
        StudioDeveloppementModel studioDeveloppementDeTest;
        EditeurModel EditeurDeTest;
        double prix;
        bool contientDuContenuSupplementaire;


        [SetUp]
        public void Setup()
        {
            this.nomJeu = "Jeu de Test";
            this.listeEnumGenreDeJeu = new HashSet<EnumGenreDeJeu>() { EnumGenreDeJeu.ACTION, EnumGenreDeJeu.COMBAT };
            this.listeEnumCaracteristiqueDeJeu = new HashSet<EnumCaracteristiqueDeJeu>() { EnumCaracteristiqueDeJeu.SOLO, EnumCaracteristiqueDeJeu.PVE };
            this.listeEnumLangagesDeJeu = new HashSet<EnumLangagesDeJeu>() { EnumLangagesDeJeu.FRANCAIS, EnumLangagesDeJeu.ANGLAIS };
            this.dateDeSortie = new DateTime(2017, 09, 06);
            this.studioDeveloppementDeTest = new StudioDeveloppementModel("Studio De Test", "Description test");
            this.EditeurDeTest = new EditeurModel("Éditeur De Test", "Description test");
            this.prix = 79.99;
            this.contientDuContenuSupplementaire = false;

            this.jeuUnderTest = new JeuModel();
            jeuUnderTest.NomDuJeu = nomJeu;
            jeuUnderTest.GenresDuJeu = listeEnumGenreDeJeu;
            jeuUnderTest.CaracteristiquesDuJeu = listeEnumCaracteristiqueDeJeu;
            jeuUnderTest.LangagesDuJeu = listeEnumLangagesDeJeu;
            jeuUnderTest.DateDeSortie = dateDeSortie;
            jeuUnderTest.StudioDeDeveloppement = studioDeveloppementDeTest;
            jeuUnderTest.Editeur = EditeurDeTest;
            jeuUnderTest.Prix = prix;
            jeuUnderTest.ContientDuContenuSupplementaire = contientDuContenuSupplementaire;

        }

        [Test]
        public void etantDonneDesObjets_lorsquonInitialiseLeConstructeur_alorsCreeUnJeuProprement()
        {
            // arrange
            // Voir Setup()

            // act
            // Constructeur appeler dans Setup()

            // assert
            Assert.That(this.jeuUnderTest, Is.Not.Null);
            Assert.That(this.jeuUnderTest.NomDuJeu, Is.EqualTo(nomJeu));
            Assert.That(this.jeuUnderTest.GenresDuJeu, Is.EqualTo(listeEnumGenreDeJeu));
            Assert.That(this.jeuUnderTest.CaracteristiquesDuJeu, Is.EqualTo(listeEnumCaracteristiqueDeJeu));
            Assert.That(this.jeuUnderTest.LangagesDuJeu, Is.EqualTo(listeEnumLangagesDeJeu));
            Assert.That(this.jeuUnderTest.DateDeSortie, Is.EqualTo(dateDeSortie));
            Assert.That(this.jeuUnderTest.StudioDeDeveloppement, Is.EqualTo(studioDeveloppementDeTest));
            Assert.That(this.jeuUnderTest.Editeur, Is.EqualTo(EditeurDeTest));
            Assert.That(this.jeuUnderTest.Prix, Is.EqualTo(prix));
            Assert.That(this.jeuUnderTest.ContientDuContenuSupplementaire, Is.EqualTo(contientDuContenuSupplementaire));
        }

        [Test]
        public void etantDonneUnJeu_quandOnGetSonNom_alorsIlEstBienRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            string nomRetourne = jeuUnderTest.NomDuJeu;

            // assert
            Assert.That(nomRetourne, Is.EqualTo(nomJeu));
        }

        [Test]
        public void etantDonneUnJeu_quandOnSetSonNom_alorsIlEstBienAffecte()
        {
            // arrange
            // Voir Setup()
            string nouvNom = "nouvNom";

            // act
            jeuUnderTest.NomDuJeu = nouvNom;

            // assert
            Assert.That(jeuUnderTest.NomDuJeu, Is.EqualTo(nouvNom));
        }

        [Test]
        public void etantDonneUnJeu_quandOnGetSonPrix_alorsIlEstBienRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            double prixRetourne = jeuUnderTest.Prix;

            // assert
            Assert.That(prix, Is.EqualTo(prix));
        }

        [Test]
        public void etantDonneUnJeu_quandOnSetSonPrix_alorsIlEstBienAffecte()
        {
            // arrange
            // Voir Setup()
            double nouvPrix = 1;

            // act
            jeuUnderTest.Prix = nouvPrix;

            // assert
            Assert.That(jeuUnderTest.Prix, Is.EqualTo(nouvPrix));
        }

        [Test]
        public void etandDonneDesJeuxDifferents_quandOnLesCompare_alorsDeterminePasEgale()
        {
            // arrange
            // Voir Setup()
            JeuModel jeuDifferent = new JeuModel();
            jeuDifferent.NomDuJeu = "différent";
            jeuDifferent.GenresDuJeu = listeEnumGenreDeJeu;
            jeuDifferent.CaracteristiquesDuJeu = listeEnumCaracteristiqueDeJeu;
            jeuDifferent.LangagesDuJeu = listeEnumLangagesDeJeu;
            jeuDifferent.DateDeSortie = dateDeSortie;
            jeuDifferent.StudioDeDeveloppement = studioDeveloppementDeTest;
            jeuDifferent.Editeur = EditeurDeTest;
            jeuDifferent.Prix = prix;
            jeuDifferent.ContientDuContenuSupplementaire = contientDuContenuSupplementaire;

            // act
            bool egalite = jeuDifferent.Equals(jeuUnderTest);

            // assert
            Assert.False(egalite);
        }

        [Test]
        public void etantDonneUnJeu_quandOnLeConvertiEnString_alorsIlRetourneLeBonFormat()
        {
            // arrange
            // Voir Setup()
            string stringAttendu = "NomDuJeu : Jeu de Test\n" +
                "GenresDuJeu : \n\t[ACTION, COMBAT]\n" +
                "CaracteristiquesDuJeu : \n\t[SOLO, PVE]\n" +
                "LangagesDuJeu : \n\t[FRANCAIS, ANGLAIS]\n" +
                "DateDeSortie : 2017-09-06 00:00:00\n" +
                "StudioDeDeveloppement : Studio De Test\n" +
                "Editeur : Éditeur De Test\nPrix : 79,99\n" +
                "ContientDuContenuSupplementaire : False\n";

            // act
            string jeuEnString = jeuUnderTest.ToString();
            Console.WriteLine(jeuEnString);

            // assert
            Assert.That(jeuEnString, Is.EqualTo(stringAttendu));
        }

        [Test]
        public void etandDonneUnJeu_quandOnLeTransformEnString_alorsLeStringRespecteLeFormat()
        {
            // arrange
            // Voir Setup()
            string resultatAttendu = "NomDuJeu : Jeu de Test\n" +
                "GenresDuJeu : \n\t[ACTION, COMBAT]\n" +
                "CaracteristiquesDuJeu : \n\t[SOLO, PVE]\n" +
                "LangagesDuJeu : \n\t[FRANCAIS, ANGLAIS]\n" +
                "DateDeSortie : 2017-09-06 00:00:00\n" +
                "StudioDeDeveloppement : Studio De Test\n" +
                "Editeur : Éditeur De Test\nPrix : 79,99\n" +
                "ContientDuContenuSupplementaire : False\n";

            // act
            string resultatObtenu = jeuUnderTest.ToString();
            Console.WriteLine(resultatObtenu);

            // assert
            Assert.That(resultatObtenu, Is.EqualTo(resultatAttendu));
        }
    }
}