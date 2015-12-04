package skyline.trip;

import de.plusgis.index.rtree.HyperBoundingBox;
import de.plusgis.index.rtree.HyperPoint;
import de.plusgis.index.rtree.RTree;
import de.plusgis.index.rtree.RTreeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by bimurto on 03-Dec-15.
 */
public class RTreeFileMaker3d {
//    String filename = "DataSet/5d/RTREEFIL5D";
//    String filename = "DataSet/4d/RTREEFIL4D";
    String filename = "DataSet/3d/RTREEFIL3D";

    RTree tree[] = new RTree[63];
    int dimension = 5;
    int maxLoad = 4;
    HyperBoundingBox maxBox = null;
    public RTreeFileMaker3d(){
//        for(int i=0;i<63;i++){
//            String name = filename + i;
//            try {
//                tree[i] = new RTree(dimension,maxLoad,name);
//            } catch (RTreeException e) {
//                e.printStackTrace();
//            }
//        }
//        readAndInsert();

        createMaxBox(dimension);
        treeWalk(62);
    }

    public void readAndInsert(){
        Scanner input = null;
        try {
            input = new Scanner(new File("Input5d.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(input.hasNext()){
            double x = input.nextDouble();
            double y = input.nextDouble();
            double z = input.nextDouble();
            double w = input.nextDouble();
            double u = input.nextDouble();
            int type = input.nextInt();
            HyperPoint min = new HyperPoint(new double[]{x,y,z,w,u});
            HyperPoint max = new HyperPoint(new double[]{x,y,z,w,u});
            HyperBoundingBox rect = new HyperBoundingBox(min, max);


            try {
                tree[type].insert(new Integer(type), rect);
            } catch (RTreeException e) {
                e.printStackTrace();
            }

        }
    }

    public void createMaxBox(int dim){
        double max[] = new double[dim];
        double min[] = new double[dim];
        for(int i=0;i<dim;i++){
            max[i] = 1000;
            min[i] = -1000;
        }
        HyperPoint maxPoint = new HyperPoint(max);
        HyperPoint minPoint = new HyperPoint(min);
        maxBox = new HyperBoundingBox(minPoint, maxPoint);
        System.out.println(maxBox);
    }

    public void treeWalk(int type){

        try {
            RTree  tree = new RTree(filename+type);
            tree.retrieve(maxBox);
        } catch (RTreeException e) {
            e.printStackTrace();
        }

    }
}
