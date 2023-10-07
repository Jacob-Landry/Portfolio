namespace TestRevueJeu
{
    public class EditeurModelTest
    {
        
        string nomEditeur;
        string descriptionEditeur;

        EditeurModel editeurUnderTest;

        [SetUp]
        public void Setup()
        {
            nomEditeur = "Editeur de test";
            descriptionEditeur = "Description test";

            editeurUnderTest = new EditeurModel(nomEditeur, descriptionEditeur);
        }

        [Test]
        public void etantDonneUnEditeur_quandOnSetSonNom_alorsLeNomEstModifie()
        {
            // arrange
            // Voir Setup()
            string nouveauNom = "NouvelEditeur";

            // act
            editeurUnderTest.Nom = nouveauNom;

            // assert
            Assert.That(editeurUnderTest.Nom, Is.EqualTo(nouveauNom));
        }

        [Test]
        public void etantDonneUnEditeur_quandOnGetSonNom_alorsLeNomEstRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            string nomRetourne = editeurUnderTest.Nom;

            // assert
            Assert.That(nomRetourne, Is.EqualTo(nomEditeur));
        }

        [Test]
        public void etantDonneUnEditeur_quandOnSetSaDescription_alorsSaDescriptionEstModifie()
        {
            // arrange
            // Voir Setup()
            string nouvelleDescription = "NouvelleDescription";

            // act
            editeurUnderTest.Description = nouvelleDescription;

            // assert
            Assert.That(editeurUnderTest.Description, Is.EqualTo(nouvelleDescription));
        }

        [Test]
        public void etantDonneUnEditeur_quandOnGetSaDescription_alorsSaDescriptionEstRetourne()
        {
            // arrange
            // Voir Setup()

            // act
            string descriptionRetourne = editeurUnderTest.Description;

            // assert
            Assert.That(descriptionRetourne, Is.EqualTo(descriptionEditeur));
        }

        [Test]
        public void etantDonneDesObjets_lorsquonInitialiseLeConstructeur_alorsCreeUnEditeurProprement()
        {
            // arrange
            // Voir Setup()

            // act
            // Constructeur appeler dans Setup()

            // assert
            Assert.That(editeurUnderTest, Is.Not.Null);
            Assert.That(editeurUnderTest.Nom, Is.EqualTo(nomEditeur));
            Assert.That(editeurUnderTest.Description, Is.EqualTo(descriptionEditeur));
        }

        [Test]
        public void etantDonneUnEditeur_quandOnLeTransformeEnString_alorsLeFormatDeStringEstRespecte()
        {
            // arrange
            // Voir Setup()
            string stringAttendu = "Nom : Editeur de test\nDescription : Description test\n";

            // act
            string stringRetourne = editeurUnderTest.ToString();

            // assert
            Assert.That(stringRetourne, Is.EqualTo(stringAttendu));
        }
    }
}