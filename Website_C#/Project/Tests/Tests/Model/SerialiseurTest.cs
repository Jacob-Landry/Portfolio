namespace Tests.Model
{
    public class SerialiseurTest
    {
        
        StudioDeveloppementModel studioTest;
        EditeurModel editeurTest;
        JeuModel jeuTest;

        [SetUp]
        public void Setup()
        {
            studioTest = new StudioDeveloppementModel("Studio De Test", "Description test");
            editeurTest = new EditeurModel("Éditeur De Test", "Description test");
            jeuTest = new JeuModel("nomtest", new DateTime(), 0.00, false, 1, 1, 1);
            jeuTest.StudioDeDeveloppement = studioTest;
            jeuTest.Editeur = editeurTest;

        }

        [Test]
        public void etantDonneUnTypeEtUnNom_lorsquonSerialiseEtDeserialise_alorsDoitEtreFaitProprement()
        {
            // arrange
            // voir Setup()

            // act
            Serialiseur.Serialiser(jeuTest, jeuTest.NomDuJeu);
            JeuModel serialiseurTestChargeJeu = Serialiseur.Charger<JeuModel>(jeuTest.NomDuJeu);

            // assert
            Assert.That(serialiseurTestChargeJeu, Is.Not.Null);
            Assert.That(serialiseurTestChargeJeu.NomDuJeu, Is.EqualTo(jeuTest.NomDuJeu));
            Assert.That(serialiseurTestChargeJeu.GenresDuJeu, Is.EqualTo(jeuTest.GenresDuJeu));
            Assert.That(serialiseurTestChargeJeu.CaracteristiquesDuJeu, Is.EqualTo(jeuTest.CaracteristiquesDuJeu));
            Assert.That(serialiseurTestChargeJeu.LangagesDuJeu, Is.EqualTo(jeuTest.LangagesDuJeu));
            Assert.That(serialiseurTestChargeJeu.DateDeSortie, Is.EqualTo(jeuTest.DateDeSortie));
            Assert.That(serialiseurTestChargeJeu.StudioDeDeveloppement.Nom, Is.EqualTo(jeuTest.StudioDeDeveloppement.Nom));
            Assert.That(serialiseurTestChargeJeu.StudioDeDeveloppement.Description, Is.EqualTo(jeuTest.StudioDeDeveloppement.Description));
            Assert.That(serialiseurTestChargeJeu.Editeur.Nom, Is.EqualTo(jeuTest.Editeur.Nom));
            Assert.That(serialiseurTestChargeJeu.Editeur.Description, Is.EqualTo(jeuTest.Editeur.Description));
            Assert.That(serialiseurTestChargeJeu.Prix, Is.EqualTo(jeuTest.Prix));
            Assert.That(serialiseurTestChargeJeu.ContientDuContenuSupplementaire, Is.EqualTo(jeuTest.ContientDuContenuSupplementaire));
        }
    }
}