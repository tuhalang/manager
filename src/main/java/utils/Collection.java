package utils;

import java.util.ArrayList;
import java.util.List;

public class Collection<T> {

    public List< T > subCollection(List< T > list, int start, int end){

        List< T > newList = new ArrayList<T>();
        for(int i=0; i<list.size(); i++){
            if(i >= start && i < end){
                newList.add(list.get(i));
            }
        }

        return newList;

    }
}
