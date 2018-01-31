import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }
    
    /**
     * Imprime los detalles de las subastas con al menos una puja.
     */
    public void close() 
    {
        for(Lot lote : lots) {
            Bid highestBid = lote.getHighestBid();
            if(highestBid != null) {
                System.out.println(lote);
            }
            else {
                System.out.println(lote.getNumber() + ": No tiene pujas");
            }
        }
    }
    
    /**
     * @return Devuelve una coleccion con todos los lotes no vendidos.
     */
    public ArrayList<Lot> getUnsold() {
        ArrayList<Lot> unsoldLots = new ArrayList<>();
        for(Lot lote : lots) {
            Bid highestBid = lote.getHighestBid();
            if(highestBid != null) {
                unsoldLots.add(lote);
            }
        }
        return unsoldLots;
    }
    
    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int number){
        Lot finalLot = null;
        boolean found = false;
        int i = 0;
        while (!found && i < lots.size()){
            Lot actualLot = lots.get(i);
            if(actualLot.getNumber() == number) {
                found = true;
                finalLot = actualLot;
            }
        }
        return finalLot;
    }
    
    /**
     * Elimina el lote con el numero de
     * lote especificado.
     * @param number el numero del lote a borrar.
     * @return El lote con el numero dado o null si no existe.
     */
    public Lot removeLot(int number){
        Lot finalLot = getLot(number);
        lots.remove(finalLot);
        return finalLot;
    }
}
