1. SQL SERVER : Requête 
1.1
SELECT TOP 150 
    C.Nom,
    A.Adresse,
    A.Ville,
    A.CP
FROM Client C
LEFT JOIN Adresse A ON C.AdresseID = A.ID
ORDER BY C.Date ASC;
1.2
SELECT TOP 150 
    C.Nom,
    A.Adresse,
    A.Ville,
    A.CP,
    (SELECT COUNT(*) 
     FROM Client C2 
     WHERE C2.AdresseID = C.AdresseID) AS NombreClients
FROM Client C
LEFT JOIN Adresse A ON C.AdresseID = A.ID
ORDER BY C.Date ASC;

2. SQL SERVER : Conception 
2.1
        Colonnes :
Nom de colonne	    Type de données	        Description
ID	                    int	                Identifiant unique du résultat
RapportID	            int	                Référence au rapport associé (clé étrangère)
TypeResultatID	        int	                Référence au type de résultat (clé étrangère)
CreationDate	        datetime	        Date de création du résultat
Commentaire	            nvarchar(1024)	    Commentaire du résultat

        Clé primaire :  
            ID (colonne unique et identifiante)

        Clés étrangères :
            RapportID → fait référence à Rapport(ID)

            TypeResultatID → fait référence à TypeResultat(ID)
2.2
2. Deux solutions pour définir automatiquement CreationDate à la date UTC
Solution 1 : Valeur par défaut dans le schéma SQL
Définir une valeur par défaut pour la colonne CreationDate dans la table.
Utiliser la fonction SQL Server GETUTCDATE() comme valeur par défaut.

Solution 2 : Définir la valeur côté application
Lors de l’insertion d’un nouveau résultat depuis l’application (ex: via C#), initialiser le champ CreationDate avec la date/heure UTC actuelle avant l’envoi à la base.

3. Algorithme en C# 

using System;

class Program
{
    static void Main()
    {
        for (int n = 1; n <= 1000; n++)
        {
            if (n % 7 == 0 && n % 11 == 0)
            {
                Console.WriteLine("Acceo");
            }
            else if (n % 7 == 0)
            {
                Console.WriteLine("Ac");
            }
            else if (n % 11 == 0)
            {
                Console.WriteLine("ceo");
            }
            else
            {
                Console.WriteLine(n);
            }
        }
    }
}

4. Linq et optimisation 
4.1
public static void UpdateCorrespondances()
{
    var DB = Helper.CreateDBContexte();
    List<(string nomAvant, string nomApres)> ListeCorrespondances = ReadCorrespondancesFromFile();

    var correspondanceDict = ListeCorrespondances.ToDictionary(x => x.nomAvant, x => x.nomApres);
    var nomsAVerifier = correspondanceDict.Keys.ToList();
    var societesAModifier = DB.Societes
        .Where(s => nomsAVerifier.Contains(s.Nom))
        .ToList();

    foreach (var societe in societesAModifier)
    {
        societe.Nom = correspondanceDict[societe.Nom];
    }

    DB.SaveChanges();
}

4.2
public Societe[] GetSocieteAcceos(DataContext DB)
{
    return DB.Societes
             .Where(s => s.Nom.StartsWith("Acceo"))
             .ToArray();
}

4.3
public List<string> GetNomEffectifs(DataContext DB)
{
    return DB.Societes
             .Where(s => s.Effectif >= 200)
             .Select(s => $"{s.Nom} – {s.Effectif}")
             .ToList();
}

5. C# et WPF 
5.1 
* Dans le XAML :
<Window x:Class="TestDecimal.MainWindow"
        xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
        xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"
        xmlns:d="http://schemas.microsoft.com/expression/blend/2008"
        xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
        xmlns:local="clr-namespace:TestDecimal"
        mc:Ignorable="d"
        Title="MainWindow" Height="450" Width="800"
        x:Name="This">

    <Window.Resources>
        <local:IsTrueToVisibleConverter x:Key="IsTrueToVisibleConverter" />
    </Window.Resources>

    <StackPanel>
        <!-- Liaison de ChkActive à VM.IsActive -->
        <CheckBox Name="ChkActive"
                  Content="Activer"
                  IsChecked="{Binding IsActive, Mode=TwoWay}" />
        
        <Button Name="BtWrite"
                Content="Écrire"
                Click="BtWrite_Click" /> <!-- Liaison à l’événement -->
        
        <TextBox Name="TbResultat"
                 AcceptsReturn="True" />
    </StackPanel>
</Window>

* Dans le code-behind (Window.xaml.cs):
public partial class MainWindow : Window
{
    public MyViewModel VM { get; }

    public MainWindow()
    {
        InitializeComponent();
        VM = new MyViewModel();
        this.DataContext = VM;
    }

    protected void BtWrite_Click(object sender, RoutedEventArgs e)
    {
        // écriture dans le TextBox selon la propriété IsActive
        TbResultat.Text = $"Le CheckBox est {(VM.IsActive ? "actif" : "inactif")}";
    }
}

5.2
*Dans le XAML
    <Button Name="BtWrite"
        Content="Écrire"
        Click="BtWrite_Click" />

* Dans le code-behind (Window.xaml.cs):
    protected void BtWrite_Click(object sender, RoutedEventArgs e)
    {
        // Pour afficher l'etat du CheckBox dans le TextBox
        TbResultat.Text = $"Le CheckBox est {(VM.IsActive ? "actif" : "inactif")}";
    }

5.3
protected void BtWrite_Click(object sender, RoutedEventArgs e)
{
    if (VM.IsActive)
    {
        TbResultat.Text = "Je suis actif";
    }
    else
    {
        MessageBox.Show("Non actif");
    }
}

5.4
a- Avec Converter IsTrueToVisibleConverter
*Dans le XAML
<TextBox Name="TbResultat"
         AcceptsReturn="True"
         Visibility="{Binding IsActive, Converter={StaticResource IsTrueToVisibleConverter}}" />

b- Sans Converter, uniquement en XAML
*Dans le XAML
<Window.Resources>
    <BooleanToVisibilityConverter x:Key="BoolToVis" />
</Window.Resources>

<TextBox Name="TbResultat"
         AcceptsReturn="True"
         Visibility="{Binding IsActive, Converter={StaticResource BoolToVis}}" />

6. Débogage
6.1 
Probleme:
    DataResult result = null;
Solution:
    Ajouter une classe pour DataResult
    public class DataResult
    {
        public string Nom { get; set; }
    }

6.2
Probleme:
    La variable result pourrait rester null si aucune des deux conditions CheckSomeCondition() ou CheckSomeCondition2() n’est vraie. 
    Donc 
    Console.WriteLine(result.Nom);
    va déclencher une NullReferenceException si result est toujours null.
Solution
    Ajouter une vérification avant d'accéder à result.Nom
    if (result != null)
    {
        Console.WriteLine(result.Nom);
    }
    else
    {
        Console.WriteLine("Aucun résultat généré.");
    }

7. MVC Razor 
Dans Index.cshtml:

@model MaPageModel

<table>
    <thead>
        <tr>
            <th>Nom</th>
            <th>Quantité</th>
            <th>Actif ?</th>
        </tr>
    </thead>
    <tbody>
        @foreach (var ligne in Model.Lignes)
        {
            <tr>
                <td>@ligne.Nom</td>
                <td>@ligne.Quantite</td>
                <td>@(ligne.IsActif ? "oui" : "non")</td>
            </tr>
        }
    </tbody>
</table>













