package es.peenyaa7.examplespringbootmicrosoftexcel.classes;

import java.util.ArrayList;
import java.util.List;

import es.peenyaa7.examplespringbootmicrosoftexcel.models.Lovebird;
import es.peenyaa7.examplespringbootmicrosoftexcel.models.Owner;

/**
 * Esta clase simula una base de datos. Devuelve los datos de la misma manera que
 * lo haría una base de datos JPA.
 */
public class Database {
    
    private List<Owner> owners;

    private List<Lovebird> lovebirds;

    public Database() {
        this.owners = new ArrayList<>();
        this.lovebirds = new ArrayList<>();
    }

    public Owner save(Owner owner) {
        owners.add(owner);
        System.out.println("Owner saved: " + owner);
        return owner;
    }

    public Lovebird save(Lovebird lovebird) {
        lovebirds.add(lovebird);
        System.out.println("Lovebird saved: " + lovebird);
        return lovebird;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public Owner update(Owner owner) {
        Owner ownerToUpdate = owners.stream().filter(o -> o.getId().equals(owner.getId())).findFirst().get();
        ownerToUpdate.setName(owner.getName());
        ownerToUpdate.setSurname(owner.getSurname());
        ownerToUpdate.setDni(owner.getDni());
        ownerToUpdate.setLovebirds(owner.getLovebirds());
        System.out.println("Owner updated: " + ownerToUpdate);
        return ownerToUpdate;
    }

    public Lovebird update(Lovebird lovebird) {
        Lovebird lovebirdToUpdate = lovebirds.stream().filter(l -> l.getId().equals(lovebird.getId())).findFirst().get();
        lovebirdToUpdate.setAlias(lovebird.getAlias());
        System.out.println("Lovebird updated: " + lovebirdToUpdate);
        return lovebirdToUpdate;
    }

    public void delete(Owner owner) {
        owners.remove(owner);
        System.out.println("Owner deleted: " + owner);
    }

    public void delete(Lovebird lovebird) {
        lovebirds.remove(lovebird);
        System.out.println("Lovebird deleted: " + lovebird);
    }

    public List<Lovebird> getLovebirds() {
        return lovebirds;
    }


}
