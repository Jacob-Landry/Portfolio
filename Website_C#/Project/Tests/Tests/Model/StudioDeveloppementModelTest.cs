namespace TestRevueJeu
{
    public class StudioDeveloppementModelTest
    {

        string nomStudio;
        string descriptionStudio;

        StudioDeveloppementModel studioUnderTest;

        [SetUp]
        public void Setup()
        {
            nomStudio = "Studio de test";
            descriptionStudio = "Description test";

            studioUnderTest = new StudioDeveloppementModel(nomStudio, descriptionStudio);
        }

        [Test]
        public void etantDonneDesObjets_lorsquonInitialiseLeConstructeur_alorsCreeUnStudioProprement()
        {
            // arrange
            // Voir Setup()

            // act
            // Constructeur appeler dans Setup()

            // assert
            Assert.That(studioUnderTest, Is.Not.Null);
            Assert.That(studioUnderTest.Nom, Is.EqualTo(nomStudio));
            Assert.That(studioUnderTest.Description, Is.EqualTo(descriptionStudio));
        }

        [Test]
        public void etantDonneUnStudio_quandOnLeTransformeEnString_alorsLeFormatDoitEtreRespecte()
        {
            // arrange
            // Voir Setup()
            string resultatAttendu = "Nom : Studio de test\nDescription : Description test\n";

            // act
            string resultatObtenu = studioUnderTest.ToString();

            // assert
            Assert.That(resultatObtenu, Is.EqualTo(resultatAttendu));
        }

    }
}