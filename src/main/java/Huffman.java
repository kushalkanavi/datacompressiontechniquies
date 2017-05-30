import java.util.ArrayList;

/**
 * Created by kushalkanavi on 5/29/17.
 */
public class Huffman {
    String data;

    public Huffman(String data){
        this.data = data;
    }

    public  void Start_point(){
        String strtxt = data;
        char[] ch = strtxt.toCharArray();
        ArrayList<Character> letters = new ArrayList<Character>();

        for (int i = 0; i < ch.length; i++) {
            if (!(letters.contains(ch[i]))) {
                letters.add(ch[i]);
            }
        }

        int[] countOfChar = new int[letters.size()];

        for (int i = 0; i < letters.size(); i++) {
            char checker = letters.get(i);
            for (int x = 0; x < ch.length; x++) {
                if (checker == ch[x]) {
                    countOfChar[i]++;
                }
            }
        }

        //Sorting in Ascending Order
        for (int i = 0; i < countOfChar.length - 1; i++) {
            for (int j = 0; j < countOfChar.length - 1; j++) {
                if (countOfChar[j] < countOfChar[j + 1]) {
                    int temp = countOfChar[j];
                    countOfChar[j] = countOfChar[j + 1];
                    countOfChar[j + 1] = temp;

                    char tempChar = letters.get(j);
                    letters.set(j, letters.get(j + 1));
                    letters.set(j + 1, tempChar);
                }
            }
        }

        for (int x = 0; x < countOfChar.length; x++) {
            Insert_Leaf(letters.get(x).toString(),countOfChar[x]);
        }
        Node root = build_tree();
        huffmanCode(root,data,countOfChar,letters);
    }

    Node head = null;
    Node current = null;
    Node temp = null;

    Node Insert_Leaf(String letter, int weight){
        if(head == null){
            Node temp = new Node();

            temp.letter = letter;
            temp.weight = weight;
            temp.next = null;
            temp.previuos = null;
            head = current = temp;
        }

        else{
            Node temp = new Node();

            temp.letter = letter;
            temp.weight = weight;
            temp.next = null;
            current.next = temp;
            temp.previuos = current;
            current = temp;
        }
        return temp;
    }

    public void print() {
        Node temp = head;

        System.out.println("");

        while (temp != null)
        {
            System.out.print(temp.letter +"-"+ temp.weight + " > ");
            temp = temp.next;
        }
        System.out.println("\n");
    }

    public void huffmanCode(Node root,String txt,int[] countOfChar,ArrayList<Character> characters){

        String codedString = "";
        char[] messageArray = txt.toCharArray();
        char checker;

        if (root != null)
        {
            for (int i = 0; i < messageArray.length; i++) {
                current = root;
                checker = messageArray[i];
                String code = "";
                while (true) {
                    if (current.leftchild.letter.toCharArray()[0] == checker) {
                        code += "0";
                        break;
                    } else {
                        code += "1";
                        if (current.rightchild != null) {
                            if (current.rightchild.letter.toCharArray()[0] == characters
                                    .get(countOfChar.length - 1)) {
                                break;
                            }
                            current = current.rightchild;
                        } else {
                            break;
                        }
                    }
                }
                codedString += code;
            }
            System.out.println();
            System.out.println("For the string: "+data);
            System.out.println("The coded string is: " + codedString);
        }

        else
            System.out.println("No tree to produce huffman code");
    }

    public Node build_tree() {
        Node temp = head;
        Node root = head;

        while (temp.next != null) {
            temp = temp.next;
            root = root.next;
        }

        while (temp.previuos!= null)
        {
            temp = temp.previuos;
            root = Insert_tree(temp,root);
            //System.out.println(root.letter+">"+root.weight);
        }
        /*System.out.println("\nPre-order :");
        preOrder(root);
        System.out.println("\nInorder :");
        inOrder(root);
        System.out.println("\nPost-order :");
        postOrder(root);*/

        return root;
    }

    Node Insert_tree(Node lchild, Node rchild){
        Node temp = new Node();
        temp.weight = lchild.weight + rchild.weight;
        temp.letter = lchild.letter.concat(rchild.letter);
        temp.leftchild = lchild;
        temp.rightchild = rchild;
        current = temp;
        return temp;
    }

    public static void preOrder(Node root) {

        if (root != null) {
            System.out.print("("+root.letter+"-"+root.weight+")" + "->");
            inOrder(root.leftchild);
            inOrder(root.rightchild);
        }
    }

    public static void inOrder(Node root) {

        if (root != null) {
            inOrder(root.leftchild);
            System.out.print("("+root.letter+"-"+root.weight+")" + "->");
            inOrder(root.rightchild);
        }
    }

    public static void postOrder(Node root) {

        if (root != null) {
            inOrder(root.leftchild);
            inOrder(root.rightchild);
            System.out.print("("+root.letter+"-"+root.weight+")" + "->");
        }
    }
}