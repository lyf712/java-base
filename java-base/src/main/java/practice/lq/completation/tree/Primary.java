package practice.lq.completation.tree;



import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/1
 * @VERSION 1.0
 * @DESC
 */
public class Primary {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        new StringBuffer().reverse();


        q.add(root);
        q.add(new TreeNode(-1));//标志位

        while(!q.isEmpty()){
            TreeNode temp = q.peek();
            if(temp!=null){
                if(temp.val==-1){
                    q.poll();
                    lists.add(new ArrayList<>(list));//不能直接add list
                    list.clear();
                    if(q.size()!=0)// 最后一个
                        q.add(new TreeNode(-1));
                    continue;
                }
                list.add(temp.val);
                if(temp.left!=null)
                    q.add(temp.left);
                if(temp.right!=null)
                    q.add(temp.right);
            }

            q.poll();
        }

        StringBuffer stringBuffer = new StringBuffer();
        //
        return lists;
    }
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            //   String levelNums = "";
            int level = q.size();// 该层数量
            //StringBuffer sb= new StringBuffer();
            String sb="";
            for(int i=1;i<=level;i++){
                TreeNode node = q.poll();
                //  levelNums=levelNums+node.val+"";

                if(node==null||node.val==-1){
                    //sb.append("[]");
                    System.out.println("为空");
                    sb=sb+"*";
                    continue;
                }else{
                    //sb.append(node.val+"");
                    sb=sb+node.val+"";
                    //System.out.println("---");

                    if(node.left==null&&node.right==null)
                        continue;
                }

                if(node.left!=null)
                    q.offer(node.left);
                else
                    q.offer(new TreeNode(-1));

                if(node.right!=null)
                    q.offer(node.right);
                else
                    q.offer(new TreeNode(-1));
            }


//            StringBuffer sb2 = sb.reverse();

            System.out.println(sb);

            for(int i=0,j=sb.length()-1;i<j;i++,j--){
                if(sb.charAt(i)!=sb.charAt(j)){
                    System.out.println("no");
                    return false;
                }
            }

//            System.out.print(sb.toString()+";"+sb.reverse().toString());
//            if(!sb.toString().equals(sb.reverse().toString())){ //sb.toString().equals(sb.reverse().toString()) //!sb.equals(sb2)
//                System.out.println(":yes");
//                return false;
//            }else {
//                System.out.println(":no");
//            }
        }
        return true;
    }

    public static void main(String[]args){
        // [1,2,2,null,3,null,3]
        Queue<Integer> queue = new ArrayDeque<>();
        TreeNode t= new TreeNode(1);
        t.left= new TreeNode(0);
//        t.right = new TreeNode(2);
//        t.left.right = new TreeNode(3);
//        t.right.right=new TreeNode(3);
        new Primary().isSymmetric(t);

//        new Primary().levelOrder(t).stream().forEach(e->{
//            e.stream().forEach(System.out::print);
//            System.out.println();
//        });
        //queue.poll()

    }
}
