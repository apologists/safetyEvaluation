package org.example.entity;

import java.util.*;
import java.util.Map.Entry;

/**邻接矩阵的图类
 * @author xusy
 *
 * @param <T>
 */
public class Graph<T> {

    /**
     * 用来存储顶点
     * T做为标识，vertext作为实际的顶点
     */
    private Map<T, Vertex<T>> vertexMap;

    /**
     * 图中边的数目<br>
     * 顶点的数目可以用vertexMap.size()
     */
    private int edgeCount;

    /**
     * 图是否为有向图<br>
     * 如果是有向图，则为true
     */
    boolean isDirect;

    /**图的构造函数
     * @param isDirect  图是否为有向图<br>
     * 如果是有向图，则为true
     */
    public Graph(boolean isDirect){
        vertexMap=new LinkedHashMap<>();
        edgeCount=0;
        this.isDirect=isDirect;
    }


    //下面与图的顶点相关

    /**返回图中的顶点个数
     * @return
     */
    public int getVertexCount(){
        return vertexMap.size();
    }

    /** 返回图的顶点的迭代器
     * @return
     */
    public Iterator<Vertex<T>> getVertexIterator(){
        return vertexMap.values().iterator();
    }

    /**在图中插入节点，节点的标识为label,节点的权值为cost
     * @param label
     * @param cost  如果不需要节点的权值，则设0即可
     * @return 如果图中不存在该节点，则插入，返回true<br>
     * 如果图中已经存在该节点，则更新权值，返回false
     */
    public boolean addVertex(T label,double cost){
        Vertex vertex=vertexMap.get(label);
        if(vertex!=null){
            //如果图中已经存在该节点，则更新权值，返回false
            vertex.setCost(cost);
            return false;
        }
        //如果图中不存在该节点，则插入，返回true
        vertex=new Vertex<T>(label, cost);
        vertexMap.put(label, vertex);
        return true;
    }

    //下面与图的边相关

    /** 返回图中所有的边的个数<br>
     * 如果为有向图，则是所有的有向边的个数<br>
     * 如果为无向图，则视一条边为两条相反的有向边，相当于返回无向边的个数*2
     * @return
     */
    public int getEdgeCount(){
        Iterator<Vertex<T>> iterator=getVertexIterator();
        int count=0;
        while(iterator.hasNext()){
            Vertex<T> vertex=iterator.next();
            count=count+vertex.getEdgeCount();
        }
        return count;
    }

    /** 返回图中标识为label的顶点作为出发点的边的个数
     * @param label
     * @return 如果为有向图，则返回标识为label的顶点作为出发点的边的个数
     * 如果为无向图，则返回标识为label的顶点相连接的边的个数
     * 如果图中没有这个顶点，返回-1
     */
    public int getEdgeCount(T label){
        Vertex<T> vertex=vertexMap.get(label);
        if(vertex==null){
            //如果图中没有这个顶点，返回-1
            return -1;
        }
        //返回途中标识为label的顶点作为出发点的边的个数
        return vertex.getEdgeCount();
    }

    /** 返回图中标识为label的顶点作为出发点的边的迭代器
     * @param label
     * @return 如果没有这个顶点，返回null
     */
    public Iterator<Edge> getEdgeIterator(T label){
        Vertex<T> vertex=vertexMap.get(label);
        if(vertex==null){
            //如果图中没有这个顶点，返回null
            return null;
        }
        return vertex.getEdgeIterator();
    }


    /**在图中加入一条边，如果isDirect为true，则为有向图，则<br>
     * 建立一条以begin作为标识的节点开始的边，以end作为标识的节点结束，边的权值为weight<br>
     * 如果isDirect为false，则为无向图，则<br>
     * 建立两条边，一条以begin开始，到end ，一条以end开始，到begin
     * @param begin
     * @param end
     * @param weight 如果不需要边的权值，可以设为0
     * @return 如果没有对应的边，则加入对应的边，返回true<br>
     * 如果有对应的边，则更新weight，返回false
     * 如果没有以begin或者end标识的顶点，则直接返回false
     */
    public boolean addEdge(T begin,T end,double weight){
        Vertex beginVertex=vertexMap.get(begin);
        Vertex endVertex=vertexMap.get(end);
        if(beginVertex==null||endVertex==null){
            //如果没有以begin或者end标识的顶点，则直接返回false
            return false;
        }
        //有向图和无向图都要建立begin到end的边
        //如果顶点已经与endVertex连接，那么将会更新权值，result=false
        //如果顶点没有与endVertex相连，则互相连接，result=true
        boolean result=beginVertex.connect(endVertex, weight);
        if(result){
            edgeCount++;
        }
        if(!isDirect){
            //如果不是有向图，则建立两条边,一条以end开始，到begin
            endVertex.connect(beginVertex, weight);
            if(result){
                edgeCount++;
            }
        }
        return result;
    }

    /**在图中删除一条边，如果isDirect为true，则为有向图，则<br>
     * 删除一条以begin作为标识的节点开始的边，以end作为标识的节点结束<br>
     * 如果isDirect为false，则为无向图，则<br>
     * 删除两条边，一条以begin开始，到end ，一条以end开始，到begin
     * @param begin
     * @param end
     * @return 如果有对应的边，则删除对应的边，返回true<br>
     * 如果没有有对应的边，则直接返回false
     * 如果没有以begin或者end标识的顶点，则直接返回false
     */
    public boolean removeEdge(T begin,T end){
        Vertex beginVertex=vertexMap.get(begin);
        Vertex endVertex=vertexMap.get(end);
        if(beginVertex==null||endVertex==null){
            //如果没有以begin或者end标识的顶点，则直接返回false
            return false;
        }
        //有向图和无向图都要删除begin到end的边
        //如果顶点已经与endVertex连接，那么将会删除这条边，返回true
        //如果顶点没有与endVertex连接，则啥都不做，返回false
        boolean result=beginVertex.disconnect(endVertex);
        if(result){
            edgeCount--;
        }
        if(!isDirect){
            //如果不是有向图，则删除两条边,一条以end开始，到begin
            endVertex.disconnect(beginVertex);
            if(result){
                edgeCount--;
            }
        }
        return result;
    }


    //下面与打印相关

    /**
     * 打印图的概况，所有顶点，所有边
     */
    public void printGraph(Map<String,List<String>> map){
        Iterator<Vertex<T>> iteratorVertex=getVertexIterator();
        Iterator<Edge> iteratorEdge;
        Vertex<T> vertex;
        Edge edge;
        T label;
        System.out.println("图是否为有向图："+isDirect+"，图的顶点个数："+getVertexCount()+"，图的总边个数："+getEdgeCount());
        while(iteratorVertex.hasNext()){
            List<String> list = new ArrayList<>();
            vertex=iteratorVertex.next();
            label=vertex.getLabel();
            iteratorEdge=vertex.getEdgeIterator();
            System.out.println("顶点："+label+"，以这个顶点为出发点的边的个数："+getEdgeCount(label)+"，该顶点的权值为："+vertex.getCost());
            while(iteratorEdge.hasNext()){
                edge=iteratorEdge.next();
                System.out.print("边：从 "+label+" 到 "+edge.getEndVertex().getLabel()+" ，权值："+edge.getWeight()+"   ");
                if(getEdgeCount(label) != 0){
                    list.add(String.valueOf(edge.getEndVertex().getLabel()));
                }
            }
            map.put(label.toString(),list);
            System.out.println();
        }
        System.out.println();
    }


    /**使用Floyd（弗洛伊德）算法，返回所有节点间的最短距离<br>
     * 设置两个map<br>
     * 第一个 result，key为出发点，value是map，这个map的key是结束点，value是出发点到结束点的最短距离<br>
     * 第二个 path，key为出发点，value是map，这个map的key是结束点，value是出发点到结束点的最短距离的路径的最后的中转节点<br>
     * 一开始，result里的value为maxDouble(到自己的value为0），path里的value是结束点<br>
     * 然后，用图里的所有的边对result做初始化，当不经过任意第三节点时，其最短路径为初始路径，只对图里先有的只经过两点的边，对result里的value更新<br>
     * <br>
     * 进行循环
     * 当只允许经过1号节点时，求两点之间的最短路径该如何求呢？只需判断i到1号的min距离 + 1号到j的min距离是否比i到j的min距离要小即可。
     * 如果i到1号的min距离 + 1号到j的min距离 小于 i到j的min距离，说明经过1号的路径更好，
     * 让i到j的min距离=i到1号的min距离 + 1号到j的min距离，并且i到j的最后的中转节点为1号节点
     * 比如说a到b到c到d，a到c的中转为b，a到d的中转为c（根据c得到a到c的中转为b，a到b的中转为b，就可以得到a到b到c到d）
     * 循环遍历result，便可以获取在仅仅经过1号节点时的最短距离和中转节点。
     * 由于此时result的结果已经保存了中转1号节点的最短路径，此时如果继续并入2号节点为中转节点
     * 则是任意两个节点都经过中转节点1号节点和2号节点的最短路径，把所有节点作为中转节点后，得到的是所有节点间的最短距离
     *
     *
     * @return
     */
    public Map<Vertex<T>, HashMap<Vertex<T>, Double>> getSmallestDistanceFloyd(){
        //第一个 result，key为出发点，value是map，这个map的key是结束点，value是出发点到结束点的最短距离
        Map<Vertex<T>, HashMap<Vertex<T>, Double>> result=new HashMap<>();
        //第二个 path，key为出发点，value是map，这个map的key是结束点，value是出发点到结束点的最短距离的路径的最后的中转节点
        Map<Vertex<T>, HashMap<Vertex<T>, Vertex<T>>> path=new HashMap<>();
        //Set<Vertex<T>> vertexSet=getVertexSet();
        Set<Vertex<T>> vertexSet=new HashSet<>();
        Vertex vertex;
        Edge edge;

        for(Vertex<T> begin:vertexSet){
            HashMap<Vertex<T>, Double> distanceMap=new HashMap<>();
            HashMap<Vertex<T>, Vertex<T>> pathMap=new HashMap<>();
            for(Vertex<T> end:vertexSet){
                //一开始，result里的value为maxDouble(到自己的value为0），path里的value是结束点
                distanceMap.put(end, Double.MAX_VALUE);
                pathMap.put(end, end);
            }
            //result里的value为maxDouble(到自己的value为0），path里的value是结束点
            distanceMap.put(begin, 0.0);
            result.put(begin, distanceMap);
            path.put(begin, pathMap);
        }

        for(Vertex<T> begin:vertexSet){
            HashMap<Vertex<T>, Double> distanceMap=result.get(begin);
            Iterator<Edge> edgeIterator=begin.getEdgeIterator();
            while(edgeIterator.hasNext()){
                edge=edgeIterator.next();
                //用图里的所有的边对result做初始化，当不经过任意第三节点时，其最短路径为初始路径，只对图里先有的只经过两点的边，对result里的value更新
                distanceMap.put(edge.getEndVertex(), edge.getWeight());
            }
            result.put(begin, distanceMap);
        }

        for(Vertex<T> mid:vertexSet){
            for(Vertex<T> begin:vertexSet){
                HashMap<Vertex<T>, Double> distanceMap=result.get(begin);
                HashMap<Vertex<T>, Vertex<T>> pathMap=path.get(begin);
                for(Vertex<T> end:vertexSet){
                    Double beginEnd=distanceMap.get(end);
                    Double beginMid=distanceMap.get(mid);
                    Double midEnd=result.get(mid).get(end);
                    if(beginMid==Double.MAX_VALUE||midEnd==Double.MAX_VALUE||beginMid+midEnd>beginEnd){
                        //如果通过中转点不行，或者通过中转点的距离大于原先距离，就不考虑这个中转点
                        continue;
                    }
                    //让i到j的min距离=i到1号的min距离 + 1号到j的min距离，并且i到j的最后的中转节点为1号节点
                    distanceMap.put(end, beginMid+midEnd);
                    pathMap.put(end, mid);
                }
                result.put(begin, distanceMap);
                path.put(begin, pathMap);
            }
        }

        for(Vertex<T> begin:vertexSet){
            HashMap<Vertex<T>, Double> distanceMap=result.get(begin);
            HashMap<Vertex<T>, Vertex<T>> pathMap=path.get(begin);
            for(Vertex<T> end:vertexSet){
                System.out.println("从顶点:"+begin.getLabel()+" ，到顶点："+end.getLabel()+
                        " ，最短距离为："+distanceMap.get(end)+" ，最后中转点为:"+pathMap.get(end).getLabel());
            }
        }

        return result;
    }


    /**用dijkstra算法求出first节点到其他节点的最短距离<br>
     * 声明两个set，open和close，open用于存储未遍历的节点，close用来存储已遍历的节点<br>
     * 声明两个map，一个map为distance，key为vertex，value为double，是计算现在得到的这个vertex距离起始点的最短路径<br>
     * 一个map为path，key为vertex，value为vertex，是出发点到结束点的最短距离的路径的最后的中转节点<br>
     * 初始阶段，将所有节点放入open<br>
     * distance里面先初始为doubleMax，Path先初始为vertex自己<br>
     * 将起始点放入close，设置distance=0，path=自己，更新起始点周围的节点的距离，设置他们的distance=边的距离，path=起始点<br>
     * 以初始节点为中心向外一层层遍历，获取离指定节点最近的子节点（遍历open中的vertex,找到distance最小的vertex）<br>
     * 放入close并从新计算路径，直至close包含所有子节点（或者说open为空）<br>
     * @param first
     * @return 返回一个map为distance，key为vertex，value为double，是计算现在得到的这个vertex距离起始点的最短路径<br>
     */
    public HashMap<Vertex<T>, Double> getSmallestDistanceDijkstra(String first){
        Set<Vertex<T>> open=new HashSet<>();
        Set<Vertex<T>> close=new HashSet<>();
        HashMap<Vertex<T>, Vertex<T>> path=new HashMap<>();
        HashMap<Vertex<T>, Double> distance=new HashMap<>();
        Set<Vertex<T>> set=new HashSet<>();
        //Set<Vertex<T>> set=getVertexSet();
        Vertex<T> firstVertex=vertexMap.get(first);
        Edge edge;
        if(firstVertex==null){
            return distance;
        }
        //初始阶段，将所有节点放入open,distance里面先初始为doubleMax，Path先初始为vertex自己
        for(Vertex<T> vertex:set){
            open.add(vertex);
            distance.put(vertex, Double.MAX_VALUE);
            path.put(vertex, vertex);
        }
        //将起始点放入close，设置distance=0，path=自己，更新起始点周围的节点的距离，设置他们的distance=边的距离，path=起始点
        open.remove(firstVertex);
        close.add(firstVertex);
        distance.put(firstVertex, 0.0);
        path.put(firstVertex, firstVertex);
        Iterator<Edge> edgeIterator=firstVertex.getEdgeIterator();
        while(edgeIterator.hasNext()){
            edge=edgeIterator.next();
            Vertex<T> endVertex=edge.getEndVertex();
            distance.put(endVertex, edge.getWeight());
            path.put(endVertex, firstVertex);
        }
        //以初始节点为中心向外一层层遍历，获取离指定节点最近的子节点（遍历open中的vertex,找到distance最小的vertex）
        //放入close并从新计算路径，直至close包含所有子节点（或者说open为空）
        while(!open.isEmpty()){
            Double minDistance=Double.MAX_VALUE;
            Vertex<T> minVertex=null;
            for(Vertex<T> vertex:open){
                if(minDistance>distance.get(vertex)){
                    //遍历open中的vertex,找到distance最小的vertex
                    minDistance=distance.get(vertex);
                    minVertex=vertex;
                }
            }
            open.remove(minVertex);
            close.add(minVertex);
            //System.out.println("加入节点："+minVertex.getLabel());
            edgeIterator=minVertex.getEdgeIterator();
            while(edgeIterator.hasNext()){
                edge=edgeIterator.next();
                Vertex<T> endVertex=edge.getEndVertex();
                Double weight=edge.getWeight();
                //如果之前的距离>初始到minVertex+minVertex到endVertex，就替换
                if(distance.get(endVertex)>distance.get(minVertex)+weight){
                    distance.put(endVertex, distance.get(minVertex)+weight);
                    path.put(endVertex, minVertex);
                }
            }
        }

        for(Vertex<T> vertex:set){
            System.out.println("到顶点："+vertex.getLabel()+
                    " ，最短距离为："+distance.get(vertex)+" ，最后中转点为:"+path.get(vertex).getLabel());
        }

        return distance;
    }
}
