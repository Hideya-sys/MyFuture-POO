import java.util.Scanner;

public class appweb {

    // Structure pour représenter un Client
    static class Client {
        String cin;
        String nom;
        String prenom;
        double solde;

        // Constructeur
        public Client(String cin, String nom, String prenom, double solde) {
            this.cin = cin;
            this.nom = nom;
            this.prenom = prenom;
            this.solde = solde;
        }

        // Affichage des infos
        public void afficherInfo() {
            System.out.println("\n--- Informations du Client ---");
            System.out.println("CIN     : " + this.cin);
            System.out.println("Nom     : " + this.nom);
            System.out.println("Prénom  : " + this.prenom);
            System.out.println("Solde   : " + this.solde + " DT");
        }

        // Dépôt
        public void deposer(double montant) {
            if (montant > 0) {
                this.solde += montant;
                System.out.println("Dépôt réussi ! Nouveau solde de " + this.nom + " : " + this.solde + " DT");
            } else {
                System.out.println("Montant invalide !");
            }
        }

        // Retrait
        public void retirer(double montant) {
            if (montant > 0 && montant <= this.solde) {
                this.solde -= montant;
                System.out.println("Retrait réussi ! Nouveau solde de " + this.nom + " : " + this.solde + " DT");
            } else if (montant > this.solde) {
                System.out.println("Erreur : Solde insuffisant !");
            } else {
                System.out.println("Montant invalide !");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Saisie du nombre de clients
        System.out.print("Combien de clients voulez-vous ajouter ? ");
        int nbClients = scanner.nextInt();
        scanner.nextLine(); // Nettoyage de la ligne

        // Tableau pour stocker les clients
        Client[] clients = new Client[nbClients];

        // 2. Saisie des informations des clients
        for (int i = 0; i < nbClients; i++) {
            System.out.println("\n--- Saisie du Client N°" + (i + 1) + " ---");
            
            System.out.print("Entrez le numéro CIN : ");
            String cin = scanner.nextLine();

            System.out.print("Entrez le Nom : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le Prénom : ");
            String prenom = scanner.nextLine();

            System.out.print("Entrez le Solde Initial (DT) : ");
            double solde = scanner.nextDouble();
            scanner.nextLine(); // Nettoyage de la ligne

            clients[i] = new Client(cin, nom, prenom, solde);
        }

        // 3. Menu de sélection du client et opérations
        while (true) {
            System.out.println("\n==================================");
            System.out.println("=== LISTE DES CLIENTS DISPONIBLES ===");
            for (int i = 0; i < clients.length; i++) {
                System.out.println((i + 1) + " -> " + clients[i].nom + " " + clients[i].prenom + " (CIN: " + clients[i].cin + ")");
            }
            System.out.println((clients.length + 1) + " -> Quitter l'application");
            System.out.print("Choisissez un client (numéro) : ");

            int choixClient = scanner.nextInt();

            if (choixClient == clients.length + 1) {
                System.out.println("\nMerci d'avoir utilisé notre application. Au revoir !");
                break;
            }

            if (choixClient < 1 || choixClient > clients.length) {
                System.out.println("Choix invalide ! Veuillez réessayer.");
                continue;
            }

            Client clientCourant = clients[choixClient - 1];

            int choixMenu = 0;
            do {
                System.out.println("\n--- MENU DE : " + clientCourant.nom.toUpperCase() + " " + clientCourant.prenom.toUpperCase() + " ---");
                System.out.println("1 -> Visualiser les informations du client");
                System.out.println("2 -> Déposer de l'argent");
                System.out.println("3 -> Retirer de l'argent");
                System.out.println("4 -> Retour à la liste des clients");
                System.out.print("Votre choix : ");

                choixMenu = scanner.nextInt();

                switch (choixMenu) {
                    case 1:
                        clientCourant.afficherInfo();
                        break;

                    case 2:
                        System.out.print("Entrez le montant à déposer : ");
                        double montantDepot = scanner.nextDouble();
                        clientCourant.deposer(montantDepot);
                        break;

                    case 3:
                        System.out.print("Entrez le montant à retirer : ");
                        double montantRetrait = scanner.nextDouble();
                        clientCourant.retirer(montantRetrait);
                        break;

                    case 4:
                        System.out.println("Retour à la liste des clients...");
                        break;

                    default:
                        System.out.println("Option invalide !");
                        break;
                }

            } while (choixMenu != 4);
        }

        scanner.close();
    }
}