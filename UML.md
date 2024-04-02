# Diagramme de classe UML

```mermaid
classDiagram
    Utilisateur "" <-- "" Client


class Utilisateur{
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
+ toString() String
             }
             
class Client{
        - color: String
        - filled: boolean
+ Shape(color:String,filled:boolean)
+ getColor() String
+ setColor(color:String) void
+ isFilled():boolean
+ setFilled(filled:boolean) void
+ toString() String
             }