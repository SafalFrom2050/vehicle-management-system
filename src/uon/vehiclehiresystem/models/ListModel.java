package uon.vehiclehiresystem.models;

import javax.swing.*;
import java.util.List;

public class ListModel<T> extends AbstractListModel {
    List<T> listItems;

    public ListModel(List<T> listItems){
        this.listItems = listItems;

        if(listItems.size() <= 0) return;

        listItems.add(listItems.get(0));
        listItems.set(0, null);                     // To Add Heading on Top
    }

    @Override
    public int getSize() {
        return listItems.size();
    }

    @Override
    public T getElementAt(int index) {
        return listItems.get(index);
    }
}
