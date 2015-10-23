// Based on a B specification from Marie-Laure Potet.
package source;
public class Explosives{
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];
 
    /*@ public invariant // Prop 1
      @ (0 <= nb_inc && nb_inc < 50);
      @*/
    //le nombre de paires de produits qui sont incompatible entre eux est inférieur à 50 et
    //supérieur ou égal à 0. 
    
    /*@ public invariant // Prop 2
      @ (0 <= nb_assign && nb_assign < 30);
      @*/
    //le nombre d'affectation de produits(dans les batiments) s'élève à 30 et supérieur ou égal
    //a 0. 
    
    /*@ public invariant // Prop 3
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/
    //Tous les paires de produits(qui sont incompatible) ont un 
    //nom qui commence par "Prod"
    
    /*@ public invariant // Prop 4
      @ (\forall int i; 0 <= i && i < nb_assign; 
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/
    //Les noms des batiments commencent par "Bat" et les produits sont assigné 
    //commence par "Prod".
    
    /*@ public invariant // Prop 5
      @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      @*/
    //un produit ne peut pas etre incompatible avec lui-meme.
    
    /*@ public invariant // Prop 6
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @        (\exists int j; 0 <= j && j < nb_inc; 
      @           (incomp[i][0]).equals(incomp[j][1]) 
      @              && (incomp[j][0]).equals(incomp[i][1]))); 
      @*/
    //Pour un pair de produit A et B incompatible, on a forcement un le paire B et A incompatible
    
    /*@ public invariant // Prop 7
      @ (\forall int i; 0 <= i &&  i < nb_assign; 
      @     (\forall int j; 0 <= j && j < nb_assign; 
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @        (\forall int k; 0 <= k && k < nb_inc;
      @           (!(assign[i][1]).equals(incomp[k][0])) 
      @              || (!(assign[j][1]).equals(incomp[k][1])))));
      @*/
    //Pour un batiment, on ne peut pas assigner deux produits qui sont incompatibles entre eux.


    public void add_incomp(String prod1, String prod2){
	incomp[nb_inc][0] = prod1;
	incomp[nb_inc][1] = prod2;
	incomp[nb_inc+1][1] = prod1;
	incomp[nb_inc+1][0] = prod2;
	nb_inc = nb_inc+2;
     }
    public void add_assign(String bat, String prod){
	assign[nb_assign][0] = bat;
	assign[nb_assign][1] = prod;
	nb_assign = nb_assign+1;
    }
    public void skip(){
    }
}
