import java.io.*;
import java.util.PriorityQueue;

public class MultiThreadSort {
    public static void main(String[] args) throws Exception {
        Runnable t1 = new Runnable() {
            @Override
            public void run() {
                BufferedReader br1 = null;
                BufferedWriter bw2 = null;
                String s = null;
                PriorityQueue<Integer> pr1 = new PriorityQueue<>();
                PriorityQueue<Integer> pr2 = new PriorityQueue<>();
                try {
                    bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/data01/5.txt")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    br1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:/data01/1.txt")));
                    while ((s = br1.readLine()) != null) {
                        String[] nums = s.split(" ");
                        for (int i = 0; i < nums.length; i++) {
                            pr1.add(Integer.parseInt(nums[i]));
                        }
                    }
                    br1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:/data01/2.txt")));
                    while ((s = br1.readLine()) != null) {
                        String[] nums = s.split(" ");
                        for (int i = 0; i < nums.length; i++) {
                            pr2.add(Integer.parseInt(nums[i]));
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    while (!pr1.isEmpty() && !pr2.isEmpty()) {
                        if (pr1.peek() < pr2.peek()) {
                            sb.append(pr1.peek() + " ");
                            pr1.poll();
                        } else {
                            sb.append(pr2.peek() + " ");
                            pr2.poll();
                        }
                    }
                    while (!pr1.isEmpty()) {
                        sb.append(pr1.poll() + " ");
                    }
                    while (!pr2.isEmpty()) {
                        sb.append(pr2.poll() + " ");
                    }
                    bw2.write(sb.toString());
                    bw2.flush();
                    bw2.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
       new Thread(t1).start();
    }
}
