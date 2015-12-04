package skyline.trip;

import de.plusgis.index.rtree.HyperPoint;
import de.plusgis.index.rtree.RTree;
import de.plusgis.index.rtree.RTreeException;


/**
 * Created by bimurto on 12/4/2015.
 */
public class Skyline {

    int dimension ;
    HyperPoint start = null, dest = null;
    String rtreeFile = null;
    RTree tree = null;

    public Skyline(int dimension, HyperPoint start, HyperPoint dest, String rtreeFile){
        this.dimension = dimension;
        this.start = start;
        this.dest = dest;
        this.rtreeFile = rtreeFile;
        try {
            tree = new RTree(rtreeFile);
        } catch (RTreeException e) {
            e.printStackTrace();
        }
    }


}
