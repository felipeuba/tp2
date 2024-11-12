package aed;

import java.util.Comparator; 

public class CustomComparator { 
    public static final Comparator<Traslado> BY_GANANCIA = (t1, t2) ->
        Integer.compare(t1.getGananciaNeta(), t2.getGananciaNeta());

    public static final Comparator<Traslado> BY_TIMESTAMP = (t1, t2) ->
        Integer.compare(- (t1.getTimestamp()), - (t2.getTimestamp()));

    public static final Comparator<Ciudad> BY_BALANCE = (c1, c2) -> {
        int res = Integer.compare(c1.getBalance(), (c2.getBalance()));
        if (res == 0){
            return Integer.compare(c1.getId(), (c2.getId()));
        }
        return res;
    };
}

