/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bigInt;
import java.util.*;
/**
 *
 * @author Anant
 */
public class List {
class Node {
		int data;
		Node link;
		Node(int x, Node s) {
			data = x; link = s;
		}
	}

	private Node start;
    private boolean isNegative;

	public List() {
		start = null;
        isNegative=false;
	}

	public static List makelist(Node s, boolean isnegative) {
		List ls = new List();
		ls.start = s;
        ls.isNegative = isnegative;
        return ls;
	}

	public boolean empty() {
		return (start == null);
	}

	public int head() {
		return start.data;
	}

    public boolean isnegative() {
        return isNegative;
    }
	public List tail() {
		return makelist(this.start.link,this.isNegative);
    }

	public void attach(int x) {
		if (empty())
            start = new Node(x,null);
        else {
            Node T = new Node(x,start);
            start = T;
        }
	}

	public List reverse() {
		List rev = new List();
		List ls = this;
            rev.isNegative = ls.isNegative;
        while (!ls.empty()) {
			int x = ls.head();
			ls = ls.tail();
			rev.attach(x);
		}
		return rev;
	}

    public List clean() {
        List ls = new List();
        ls = this;
        ls=ls.reverse();
       
        if (ls.empty()) {
            return ls.reverse();
        }
        else {
           if (ls.head()==0) {
               
               return ls.tail().reverse().clean();
           }
           else {
               
               return ls.reverse();
           }
        }
    }

    public static int length(List l1) {
        if (l1.empty())
            return 0;
        else
            return 1+(length(l1.tail()));
    }
    
// Note: append to be strictly used for two positive ints.
	public static List append(List l1,List l2) {
		if (l1.empty())
			return makelist(l2.start,l2.isNegative);
		else {
               int x = l1.head();
		       List tmp = append(l1.tail(),l2);
		       tmp.attach(x);
               return tmp;
		}
	}
    public static boolean isequal (List a, List b) {
        List l1 = makelist(a.start, a.isNegative);
        List l2 = makelist(b.start, b.isNegative);
        boolean flag = false;
        if (length(l1) != length(l2))
            return false;

        while(length(l1)>0 && !flag) {
            if (l1.head() != l2.head()) flag =true ;
            else {
                l1=l1.tail();
                l2=l2.tail();
            }
        }
        if (flag == false) return true; else return false;
    }
     public static boolean alessthanb (List a, List b) {
         if(a.empty() && b.empty())
             return false;
         else if (length(a) < length(b))
             return true;
         else if (length(a) > length(b))
             return false;
         else {
             if(a.reverse().head()<b.reverse().head())
                 return true;
             else if (a.reverse().head()>b.reverse().head())
                 return false;
             else {
                 List ta = a.reverse().tail().reverse();
                 List tb = b.reverse().tail().reverse();
               
                 return alessthanb(ta,tb);
         
             }
         }
     }

    public static int sod (List a) {
        List temp = makelist(a.start, a.isNegative);
        int ans = 0;
        while(!temp.empty()) {
            ans += temp.head();
            temp = temp.tail();
        }
        return ans;
    }
    public static List randomList (int n) {
        List r = new List();
        Random generator = new Random();
        while(n>0) {
            r.attach(generator.nextInt( 10 ));
            n--;
        }
        return r;
    }
     // Read list:
    public static List readlist(String s) {

        List ls = new List();

        int len = s.length();
        char arr[] = s.toCharArray();

        for(int i=0;i<len;i++) {
            int a = (int) arr[i] - 48;
            ls.attach(a);
        }
        
        return ls;
    }

    public static String printlist(List ls) {
         List temp = ls.reverse();
        
         String s = new String();

        
         if(ls.isNegative==true)
             s =s+"-";
         if(temp.empty())
             s=s+0;
         while(!temp.empty()) {
             s =s+temp.head();
             temp = temp.tail();
         }
         return s;
    }


     // Add functions:
     private static List addhelper(List l1, List l2, int carry) {
        if (l1.empty() && l2.empty()){
            if (carry==0) {
                List temp = new List();
                return temp;
            }
            else {
                List temp = new List();
                temp.attach(carry);
                return temp;
            }

        }
        else if (l2.empty()) {
            List ans = new List();
            ans.attach(((l1.head()+carry)%10));
            return append(ans,(addhelper(l1.tail(),l2,(l1.head()+carry)/10)));
        }
        else if(l1.empty()) {
            List ans = new List();
            ans.attach(((l2.head()+carry)%10));
            return append(ans,(addhelper(l1,l2.tail(),(l2.head()+carry)/10)));
        }
        else {
            List ans = new List();
            ans.attach(((l1.head()+l2.head()+carry)%10));
            return append(ans,(addhelper(l1.tail(),l2.tail(),(l1.head()+l2.head()+carry)/10)));
        }
    }
     public static List add(List l1, List l2) {
        if(l1.isNegative==false && l2.isNegative == false)
            return addhelper(l1,l2,0);
        else if(l1.isNegative == false && l2.isNegative==true) {
            List temp = makelist(l2.start,false);
            return sub(l1,temp);

        }
        else if(l1.isNegative == true && l2.isNegative==false) {
            List temp = makelist(l1.start,false);
            return sub(l2,temp);
        }
        else {
            List ans = addhelper(l1,l2,0);
            ans.isNegative = true;
            return ans;
        }
     }
     // Add functions end

     //Sub functions
     private static List subhelper (List l1, List l2, int borrow) {
         if(l1.empty() && l2.empty()) {
             return l1;
         }
         
         else if(l2.empty()) {
             if(l1.head() - borrow >=0) {
                 List temp = new List();
                 temp.attach(l1.head()-borrow);
                 return append(temp,subhelper(l1.tail(),l2,0));
             }
             else {
                 List temp = new List();
                 temp.attach(l1.head() + 10 - borrow);
                 return append(temp,subhelper(l1.tail(),l2,1));
             }
         }
         else {
           
            if(l1.head() - borrow >= l2.head()) {
                List temp = new List();

                temp.attach(l1.head()- l2.head() - borrow);
                return append(temp,subhelper(l1.tail(),l2.tail(),0));
            }
            else {
                List temp = new List();
                temp.attach(l1.head() + 10 - l2.head() - borrow);
                return append(temp, subhelper(l1.tail(),l2.tail(),1));
            }
         }
         
     }
     public static List sub (List l1, List l2) {
        

         if (l1.isNegative == false && l2.isNegative == false) {
             if (alessthanb(l2,l1) || isequal(l2,l1)) {
                 return subhelper(l1,l2,0).clean();
             }
             else {
                 List temp = subhelper(l2,l1,0).clean();
                 temp.isNegative = true;
                 return temp;
             }
         }
         else if (l1.isNegative == false && l2.isNegative==true) {
             List temp = makelist(l2.start,false);
             return add(l1,temp);
         }
         else if (l1.isNegative == true && l2.isNegative == false) {
             List temp = makelist(l1.start,false);
             List ans = add(temp,l2);
             ans.isNegative = true;
             return ans;
         }
         else {
             List temp1 = makelist(l1.start,false);
             List temp2 = makelist(l2.start,false);
             return sub(temp2,temp1);
         }

     }
     // Sub functions end


     // Multiplication functions start
     // function to extract first k elements of a list
     public static List multsplitk(List list, int k) {
         
         List l1 = new List();
         List ls = makelist(list.start,false);

         while(k>0) {
             l1.attach(ls.head());
             ls=ls.tail();
             k--;
         }
         return l1.reverse();
     }

     private static List multsplitrest(List list, int k) {

         List ls = makelist(list.start,false);

         for(int n=0; n<k;n++) {
             ls=ls.tail();
         }
         return ls;
     }

     public static List multBaseCase (List l1, List l2) {
         // assumes l1 is a 1 digit int
         List ans = new List();
         List ls = makelist(l1.start,false);
         while(!ls.empty()) {
             List one = new List();
             one.attach(1);
             ls = sub(ls,one);
             ans = add(ans,l2);
         }
         return ans;
     }
     private static int findk(List l1, List l2) {
         int k;
         if (length(l1)> length(l2)) {
             k = length(l2) / 2;
         }
         else
             k = length(l1) / 2;
         return k;
     }

     private static List addzeroes(List l1, int n) {
         List ans = makelist(l1.start,l1.isNegative);
         while(n>0)
         {
                ans.attach(0);
                n--;
         }
       return ans;
     }
     public static List mult (List l1,List l2) {
        if (l1.isNegative == false && l2.isNegative == false) {

            l1=l1.clean();
            l2=l2.clean();

            if (l1. empty() || l2.empty())
                return new List();
            else {
                if (length(l1)==1)
                    return multBaseCase(l1,l2);
                else if (length(l2)==1)
                    return multBaseCase(l2,l1);
                else {
                    int k = findk(l1,l2);

                    List b = multsplitk(l1,k);
                    List a = multsplitrest(l1,k);
                    List d = multsplitk(l2,k);
                    List c = multsplitrest(l2,k);

                    List u = mult(add(a,b),add(c,d));
                    List v = mult(a,c);
                    List w = mult(b,d);

                    return add(addzeroes(v,2*k), add(addzeroes(sub(u,add(v,w)),k), w));
                }
            }

        }
        else if (l1.isNegative==true && l2.isNegative == false) {
            List temp = makelist(l1.start,false);
            List ans =mult(temp,l2);
            ans.isNegative = true;
            return ans;
        }
        else if (l1.isNegative==false && l2.isNegative == true) {
            List temp = makelist(l2.start,false);
            List ans =mult(temp,l1);
            ans.isNegative = true;
            return ans;
        }
        else {
            List temp1 = makelist(l1.start, false);
            List temp2 = makelist(l2.start,false);
            return mult(temp1,temp2);
        }
     }
     // Multiplication functions end

     // Division functions start
     public static List divBaseCase (List l1, List l2) {
         l1=l1.clean();
         l2=l2.clean();
         if (l2.empty()) {
             // raise exception div by zero !!
             System.out.println("Division by zero encountered");
         }
         else if(alessthanb(l1,l2)) {
             List temp = new List();
             temp.attach(0);
             return temp;
         }
         else {
             List one = new List();
             one.attach(1);
             return add(one,divBaseCase(sub(l1,l2),l2));
         }
         return new List();
         // Just in case div by zero occurs
     }
      public static List modBaseCase (List l1, List l2) {
          l1= l1.clean();
          l2= l2.clean();
         if (l2.empty()) {
             // raise exception div by zero !!
             System.out.println("Division by zero encountered");
         }
         else {
            while(!alessthanb(l1,l2)) {
                 l1=sub(l1,l2);
         }
         return l1;
         }
         return l2;
         // Just in case div by zero occurs
     }
     public static List getlastk(List l1, int k) {
         return multsplitk(l1.reverse(),k).reverse();
     }

     public static List div (List a, List b) {
         if(a.isNegative == false && b.isNegative == false) {
         if(alessthanb(a,b))
             return new List();
         // INV: a >=b now
         else {
             List quot = new List();
             List temp = new List();
             temp = getlastk(a,length(b));
             int n = length(b);
             if(alessthanb(temp,b)) {
                 temp = getlastk(a,length(b) +1);
                 n++;
             }
             while(n>0) {
                 a = a.reverse().tail().reverse();
                 n--;
             }
             quot = append(divBaseCase(temp,b),quot);
             List mod = modBaseCase(temp,b);

             while(!a.empty()) {
                // System.out.println("UP1: temp = "+printlist(temp)+" a = "+printlist(a)+ " mod = " +printlist(mod) + " quot = "+ printlist(quot));
                 temp=append(getlastk(a,1),mod);
                 a =a.reverse().tail().reverse();
               //   System.out.println("UP2: temp = "+printlist(temp)+" a = "+printlist(a)+ " mod = " +printlist(mod) + " quot = "+ printlist(quot));
                 while(alessthanb(temp,b) && !a.empty()) {
                     temp=append(getlastk(a,1),temp);
                     a =a.reverse().tail().reverse();
                     quot.attach(0);
                 }
                 temp=temp.clean();
                   
               //  System.out.println("DOWN1: temp = "+printlist(temp)+" a = "+printlist(a)+ " mod = " +printlist(mod) + " quot = "+ printlist(quot));
                 quot = append(divBaseCase(temp,b),quot);
                 mod = modBaseCase(temp,b);
               //  System.out.println("DOWN2: temp = "+printlist(temp)+" a = "+printlist(a)+ " mod = " +printlist(mod) + " quot = "+ printlist(quot));
             }
             return quot;
         }
         }

        else if (a.isNegative==true && b.isNegative == false) {
            List temp = makelist(a.start,false);
            List ans =div(temp,b);
            ans.isNegative = true;
            return ans;
        }
        else if (a.isNegative==false && b.isNegative == true) {
            List temp = makelist(b.start,false);
            List ans =div(a,temp);
            ans.isNegative = true;
            return ans;
        }
        else {
            List temp1 = makelist(a.start, false);
            List temp2 = makelist(b.start,false);
            return div(temp1,temp2);
        }
     }
 

     //Division functions end

     public static List mod(List l1, List l2) {
         List ans = sub(l1,mult(l2,div(l1,l2)));
         if (ans.isNegative == true)
             ans = add(ans, l2);
         return ans;
     }

     public static List gcd(List l1, List l2) {
         if (l2.clean().empty())
             return l1;
         else
             return gcd(l2,mod(l1,l2));
     }
     public static List square(List a) {
         return mult(a,a);
     }
     public static List expmod(List b, List e, List m) {
         List one = new List();
         one.attach(1);
         List two = new List();
         two.attach(2);
         if (e.clean().empty())
            return one;

         else if (mod(e,two).clean().empty())
            return mod(square(expmod(b,div(e,two),m)), m);
        else
            return mod((mult(mod(b,m), expmod(b,sub(e,one),m))), m);
     }


public static List inversemodm (List x, List m) {
    List s = makelist(m.start, m.isNegative);
    List t = makelist(x.start, x.isNegative);
    List a = new List();
    List b = new List();
    b.attach(1);
    while(!t.empty()) {
        List q = div(s,t);
        List r = mod(s,t);
        s = makelist(t.start, t.isNegative);
        t = makelist(r.start, r.isNegative);
        List temp = mod(sub(a,mult(b,q)),m);
        a = makelist (b.start, b.isNegative);
        b = makelist ( temp.start, temp.isNegative);
    }
    return a;
}
}