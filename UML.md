# Diagramme de classe UML

```mermaid
classDiagram
Utilisateur --> Client
Utilisateur --> Employe
Client --> Reservation


class Utilisateur{
        <<Abstract>>
        - MdP: String
        - Email: String
        - Nom: String
        - Prenom: String
+ Utilisateur(MdP:String, Email:String, Nom: String, Prenom: String )
+ getMdP() String
+ setMdP(MdP:String) void
+ getEmail() String
+ setEmail(Email:String) void
+ getNom() String
+ setNom(Nom:String) void
+ getPrenom() String
+ setPrenom(Prenom:String) void
+ sinscrire() void
+ toString() String
             }
             
class Client{
        - adhérent: boolean
+ Client(color:String,filled:boolean)
+ getColor() String
+ setColor(color:String) void
+ isFilled():boolean
+ setFilled(filled:boolean) void
+ toString() String
             }
             
class Employe{
+ ajouterNouvelleReduction(valeurReduc: int, idVehiculeAppliquable: int) void
}

class Reservation{
        - numeroDeReservation: int
        - reduction: int
        - prixHorsReduc: float     
}