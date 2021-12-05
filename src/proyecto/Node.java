package proyecto;

class Node
{
    //linked list is bulilt for the tetris of puyos to store their positions
    int x,y;
    Node nextnode;
    Node prevnode;
    public Node(int x,int y)
    {
        this.x=x;
        this.y=y;
        nextnode=null;
        prevnode=null;
    }
    public Node(Node p)
    {
        x=p.x;
        y=p.y;
        nextnode=null;
        prevnode=null;

    }
    public void setNext(Node lnode)
    {
        nextnode=lnode;
    }
    public Node getNext()
    {
        return nextnode;
    }
    public void setPrev(Node lnode)
    {
        prevnode=lnode;
    }
    public Node getPrev()
    {
        return prevnode;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}