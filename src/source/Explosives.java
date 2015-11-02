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
    //le nombre de paires de produits incompatible est compris entre 0 et 50
    
    /*@ public invariant // Prop 2
      @ (0 <= nb_assign && nb_assign < 30);
      @*/
    
    //le nombre de produits affectés dans les batiments est compris entre 0 et 50
    /*@ public invariant // Prop 3
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/
    
    //Les produits incompatibles ont un nom qui commence par "Prod"
    
    /*@ public invariant // Prop 4
      @ (\forall int i; 0 <= i && i < nb_assign; 
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/
    //Les noms des batiments commencent par "Bat" et les produits qui leur sont assignés 
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
    //Pour un pair de produit A et B incompatible, on a forcement un paire B et A incompatible
    
    /*@ public invariant // Prop 7
      @ (\forall int i; 0 <= i &&  i < nb_assign; 
      @     (\forall int j; 0 <= j && j < nb_assign; 
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @        (\forall int k; 0 <= k && k < nb_inc;
      @           (!(assign[i][1]).equals(incomp[k][0])) 
      @              || (!(assign[j][1]).equals(incomp[k][1])))));
      @*/
    // on ne peut pas assigner deux produits qui sont incompatibles entre eux dans un batiment.


    //@requires prod1.startsWith("Prod") && prod2.startsWith("Prod") && !(prod1.equals(prod2));
    public void add_incomp(String prod1, String prod2){
	incomp[nb_inc][0] = prod1;
	incomp[nb_inc][1] = prod2;
	incomp[nb_inc+1][1] = prod1;
	incomp[nb_inc+1][0] = prod2;
	nb_inc = nb_inc+2;
     }
    //@requires bat.startsWith("Bat") && prod.startsWith("Prod") /* && bat does not contain two incompatible prod */ ;
    public void add_assign(String bat, String prod){
	assign[nb_assign][0] = bat;
	assign[nb_assign][1] = prod;
	nb_assign = nb_assign+1;
    }
    
    public void skip(){
    }
     /* fct compatible OK ! */
    public boolean compatible(String prod1, String prod2){
    	for(int i=0; i<nb_inc; i++){
    		if(incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2)) return false;
    	}
    	return true;
    }
    /* MARCHE PAS !
    public String findBat(String prod){
    	for(int i=0; i<nb_assign; i++){
    			boolean b =false;
    		for(int j=i; j<nb_assign; j++){
    			if(assign[0][j].equals(assign[0][i])){
    				if(compatible(assign[1][j], prod)){
    					b = true;
    				}
    			}
    		}
    		if(b) return assign[0][i];
    	}
    	return null;
    }*/
}
