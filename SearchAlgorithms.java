import java.util.*;

public class SearchAlgorithms {

    public static List<Game> DFS(Game s) {
        Stack<Game> stack = new Stack<>();
        Map<String, Boolean> visited = new HashMap<>();  //مشان الفيسيتد

        stack.push(s);

        while (!stack.isEmpty()) {
            Game current = stack.pop(); // popيعني بطالع اخر عنصر مضاف 

            visited.put(current.getHash(), true);
            

            if (current.isFinal()) {
                List<Game> winPathList = new ArrayList<>();
                while (current != null) { //ليرجع للحالة الابتدائية تبعي 
                    winPathList.add(current);
                    current = current.parent;
                }
                visited.size();
                System.out.println("Visited nodes: " + visited.size());
                Collections.reverse(winPathList);
                return winPathList;
            }
            

            List<Game> moves = current.PossibleMoves();  // مشان بحال ما وصل لحالة نهائية من الحالة الحالية يرجع الحركات الممكنة 
            for (Game move : moves) {
                if (!visited.containsKey(move.getHash()) || !visited.get(move.getHash())) {
                    stack.push(move);
                }
            }
        }
        return null;
    }

    public static List<Game> BFS(Game s) {
        Queue<Game> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();

        queue.add(s);

        while (!queue.isEmpty()) {
            Game current = queue.poll(); // poll يعني اول واحد فات بيطلع اول شي

            visited.put(current.getHash(), true);

            if (current.isFinal()) {
                List<Game> winPathList = new ArrayList<>();
                while (current != null) {
                    winPathList.add(current);
                    current = current.parent;
                }
                visited.size();
                System.out.println("Visited nodes: " + visited.size());
                Collections.reverse(winPathList); // اهمية الreverse ليطبع المسار بشكل معكوس لانو مشي من حالة الربح لرجع عالحالة الابتدائية

                return winPathList;
            }

            List<Game> moves = current.PossibleMoves();
            for (Game move : moves) {
                if (!visited.containsKey(move.getHash()) || !visited.get(move.getHash())) {
                    queue.add(move);
                }
            }
        }
        return null;
    }

    public static List<Game> UCS(Game s) {
        PriorityQueue<Game> priorityQueue = new PriorityQueue<>(new Comparator<Game>() {
            @Override
            public int compare(Game a, Game b) {
                int val = a.cost - b.cost; // المقصود شو كلفتي من الحالة الحالية للحالة اللي انا فيها هلأ
                if (val > 0) { 
                    return 1;
                } else if (val < 0) {
                    return -1;
                }
                return 0;
            }
        });

        Map<String, Boolean> visited = new HashMap<>();

        priorityQueue.add(s);

        while (!priorityQueue.isEmpty()) {
            Game current = priorityQueue.poll();

            visited.put(current.getHash(), true);

            if (current.isFinal()) {
                List<Game> winPathList = new ArrayList<>();
                while (current != null) {
                    winPathList.add(current);
                    current = current.parent;
                }
                visited.size();
                System.out.println("Visited nodes: " + visited.size());
                Collections.reverse(winPathList);

                return winPathList;
            }

            List<Game> moves = current.PossibleMoves();
            for (Game move : moves) {
                if (!visited.containsKey(move.getHash()) || !visited.get(move.getHash())) {
                    priorityQueue.add(move);
                }
            }
        }
        return null;
    }

    public static List<Game> AStar(Game s) {
        PriorityQueue<Game> priorityQueue = new PriorityQueue<>(new Comparator<Game>() {
            @Override
            public int compare(Game a, Game b) {
                int val = a.getF() - b.getF(); //مجموع الكلف مضاف لها القيمة التقديرية

                if (val > 0) {
                    return 1;
                } else if (val < 0) {
                    return -1;
                } else {
                    val = a.cost - b.cost;
                    if (val > 0) {
                        return 1;
                    } else if (val < 0) {
                        return -1;
                    }
                    return 0;
                }
            }
        });

        Map<String, Boolean> visited = new HashMap<>();
        Map<String, Game> inPQ = new HashMap<>();

        priorityQueue.add(s);

        while (!priorityQueue.isEmpty()) {
            Game current = priorityQueue.poll();

            visited.put(current.getHash(), true);
            inPQ.put(current.getHash(), null);

            if (current.isFinal()) {
                List<Game> winPathList = new ArrayList<>();
                while (current != null) {
                    winPathList.add(current);
                    current = current.parent;
                }
                visited.size();
                System.out.println("Visited nodes: " + visited.size());
                Collections.reverse(winPathList);

                return winPathList;
            }

            List<Game> moves = current.PossibleMoves();
            for (Game move : moves) {
                if (!visited.containsKey(move.getHash()) || !visited.get(move.getHash())) {
                    Game tmp = inPQ.get(move.getHash());
                    boolean isInPQueue = tmp != null;
                    Game neighbor;  // هوة نفسو move لكن بحسب اما tmp وهو الريفرنس الموجود بالحالة او اللي اجت بالبوسيبل و هو ريفرنس جديد
                    if (isInPQueue) {
                        neighbor = tmp;
                    } else {
                        neighbor = move;
                    }

                    int costToNeighbor = current.cost + 1;

                    if (!isInPQueue || costToNeighbor < neighbor.cost) {
                        neighbor.parent = current;
                        neighbor.cost = costToNeighbor;

                        if (!isInPQueue) {
                            priorityQueue.add(neighbor);
                            inPQ.put(neighbor.getHash(), neighbor);
                        }
                    }
                }
            }
            priorityQueue.add(priorityQueue.poll()); // مشان ترجع ترتب حالها
        }
        return null;
    }

    public static List<Game> HillClimbing(Game game){
        Game current = game;
        
        while (true) {
            if(current.isFinal()){
                List<Game> winPathList = new ArrayList<>();
                while (current != null) {
                    winPathList.add(current);
                    current = current.parent;
                }
                
                Collections.reverse(winPathList);

                return winPathList;
            }
            List<Game> neighbors = current.PossibleMoves();
            Game bestNeighbor = neighbors.get(0);
            for(Game neighbor : neighbors) {
                if(neighbor.getEstimatedHeuristicCost() < bestNeighbor.getEstimatedHeuristicCost()) {
                    bestNeighbor = neighbor;
                }
            }
            if(bestNeighbor.getEstimatedHeuristicCost() >= current.getEstimatedHeuristicCost()) {
                break;
            }
            current = bestNeighbor;
        }
        return null;
    }

}
