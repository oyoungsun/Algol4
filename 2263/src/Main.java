import java.util.Scanner;

class Node{
    int value;
    Node left;
    Node right;
    public Node(){
        this.value=0; //초기화
        left = null;
        right = null;
    }
}
public class Main {

    static int [] inorder = new int[100000];
    static int [] postorder = new int[100000];
    static int [] position = new int[100001];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for(int i=0; i<n; i++)
            inorder[i] = scan.nextInt();
        for(int i=0; i<n; i++)
            postorder[i] = scan.nextInt();
        for(int i=0; i<n; i++)
            position[inorder[i]] = i;
        find(0, n-1, 0, n-1);
    }
    public static void find(int ir, int il, int pr, int pl){

        if(ir>il || pr>pl) return;
            int root = postorder[pl];

            int rootIdx = position[root];


            int mid = rootIdx - ir;
            System.out.println(root);
            find(ir,rootIdx-1, pr, pr+mid-1);
            find(rootIdx+1, il, pr+mid, pl-1);
        }
    }

