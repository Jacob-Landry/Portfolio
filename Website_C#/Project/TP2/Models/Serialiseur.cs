using System;
using System.IO;
using System.Text.Json;

namespace RevueJeu.Controller
{
    public static class Serialiseur
    {
        private static string basePath = Environment.CurrentDirectory;

        public static void Serialiser<T>(T obj, string fileName)
        {
            string path = Path.Combine(basePath, @"Data\", fileName);

            JsonSerializerOptions options = new JsonSerializerOptions();
            options.WriteIndented = true;

            string jsonString = JsonSerializer.Serialize(obj, options);
            File.WriteAllText(path, jsonString);
        }

        public static T Charger<T>(string fileName)
        {
            string path = Path.Combine(basePath, @"Data\", fileName);
            string data = File.ReadAllText(path);

            return (T)JsonSerializer.Deserialize(data, typeof(T));
        }
    }
}
