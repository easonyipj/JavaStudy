package amazon;

import tree.TreeNode;

import java.util.Stack;

public class BasicCalculatorImpl implements BasicCalculator{
    @Override
    public double calculate(TreeNode root) {
        String s = getNifixExpression(root);
        return compute(s.split(""));
    }

    /**
     * compute the expression
     */
    private static double compute(String[] expression) {

        Stack<Double> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        for(String s : expression) {
            // push number into numStack
            if(isNumber(s)) {
                numStack.push(Double.valueOf(s));
            }

            // deal with the '+' and '-'
            if(s.charAt(0) == '+' || s.charAt(0) == '-') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    double b = numStack.pop();
                    double a = numStack.pop();
                    numStack.push(getCalculator(a, opStack.pop(), b));
                }

                if (!opStack.isEmpty() && opStack.peek() == '(')
                    opStack.pop();

                opStack.push(s.charAt(0));
            }

            // deat with the '*' and '/'
            if(s.charAt(0) == '*' || s.charAt(0) == '/') {
                while (!opStack.isEmpty() && opStack.peek() != '(' &&
                        opStack.peek() != '+' && opStack.peek() != '-') {
                    double b = numStack.pop();
                    double a = numStack.pop();
                    numStack.push(getCalculator(a, opStack.pop(), b));
                }

                if (!opStack.isEmpty() && opStack.peek() == '(')
                    opStack.pop();

                opStack.push(s.charAt(0));
            }

            // deal with the '('
            if (s.charAt(0) == '(') {
                opStack.push(s.charAt(0));
            }

            // deal with the ')'
            if(s.charAt(0) == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    double b = numStack.pop();
                    double a = numStack.pop();
                    numStack.push(getCalculator(a, opStack.pop(), b));
                }

                if (!opStack.isEmpty() && opStack.peek() == '(')
                    opStack.pop();
            }
        }

        // deal with the rest operators
        if (!opStack.isEmpty()) {
            double b = numStack.pop();
            double a = numStack.pop();
            numStack.push(getCalculator(a, opStack.pop(), b));
        }

        return numStack.peek();
    }

    /**
     * judge the whether the string is a number
     */
    private static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')
                return false;
        }
        return true;
    }

    /**
     * calculator
     */
    private static double getCalculator(double a, char operator, double b) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }

    /**
     * the tree to NifixExpression
     */
    private static String getNifixExpression(TreeNode root){
        StringBuffer sb = new StringBuffer();
        dfs(sb, root, 1);
        return sb.toString();
    }

    /**
     * change the tree to NifixExpression
     */
    private static void dfs(StringBuffer sb, TreeNode root, int deep) {
        if(root == null) {
            return;
        }else if(root.left == null && root.right == null) {
            sb.append(root.val);
        }else {
            if(deep > 1) {
                sb.append("(");
            }
            dfs(sb, root.left, deep + 1);
            sb.append(root.val);
            dfs(sb, root.right, deep + 1);
            if(deep > 1) {
                sb.append(")");
            }
        }

    }

    public static void main(String[] arsg) {
        TreeNode root = initTree();
        double d = new BasicCalculatorImpl().calculate(root);
        System.out.println(d);
    }

    /**
     * init a tree as requirements
     */
    private static TreeNode initTree(){
        TreeNode root = new TreeNode('+');
        TreeNode node1 = new TreeNode('*');
        TreeNode node2 = new TreeNode('/');
        TreeNode node3 = new TreeNode('-');
        TreeNode node4 = new TreeNode('3');
        TreeNode node5 = new TreeNode('4');
        TreeNode node6 = new TreeNode('5');
        TreeNode node7 = new TreeNode('2');
        TreeNode node8 = new TreeNode('3');

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = node7;
        node3.right = node8;

        return root;
    }
}
