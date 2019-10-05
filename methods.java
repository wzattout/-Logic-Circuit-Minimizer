import java.util.Scanner;
import java.util.Arrays;
public class methods
{
	private static Scanner x;
	public static int [] scan (int num_vars)
	{
		int num = 0;
		int a[] = new int [(int) (Math.pow(2.0 , (double) num_vars) + 1)] , i = 1;
		for(int j = 0; j < (int) (Math.pow(2.0 , (double) num_vars)) + 1; j++)
		{
			a[j] = (int) (Math.pow(2.0 , (double) num_vars)) + 1;
		}
		x = new Scanner(System.in);
		a[0] = x.nextInt();
		if(a[0] < -1)
			a[0] = -1;
		if(a[0] != -1)
		{
			while(a[i-1] != (int) (Math.pow(2.0 , (double) num_vars)) + 1)
			{
				do
				{
					a[i] = x.nextInt();
				}
				while(a[i] > (Math.pow(2.0 , (double) num_vars)) - 1);
				if(a[i] <= -1)
					a[i] = (int) (Math.pow(2.0 , (double) num_vars)) + 1;
				i ++ ;
			}
		}
		Arrays.parallelSort(a);
		for(int k = 0; k < a.length; k++)
		{
			if(a[k] >= 0 && a[k] < Math.pow(2, num_vars))
				num ++ ;
		}
		int arr[] = new int [num];
		int z = 0;
		for(int y = 0; y < a.length; y++)
		{
			if(a[y] >= 0 && a[y] < Math.pow(2, num_vars))
				arr[z++] = a [y];
		}
		return arr;
	}
	public static int [] merge (int[] array1 , int[] array2)
	{
		int aLen = array1.length;
        int bLen = array2.length;
        int[] result = new int[aLen + bLen];
        System.arraycopy(array1, 0, result, 0, aLen);
        System.arraycopy(array2, 0, result, aLen, bLen);
        Arrays.parallelSort(result);
        return result;
	}
	public static int degree (int num)
	{
		int deg = 0;
		double [] bin;
		bin = convertToBinary(num , 100);
		for(int i = 0; i < bin.length; i++)
		{
			if(bin[i] == 1)
				deg ++;
		}
		return deg;
	}
	public static double[] convertToBinary (int no , int num_vars)
	{
	    double container [] = new double[num_vars];
	    int i = 0;
	    while (no > 0){
	        container [i] = no%2;
	        i++;
	        no = no/2;
	    }
	    container = reverse(container);
	    return container;
	}
	public static double [] reverse (double[] arr )
	{
		int x = arr.length - 1;
		double [] rev = new double [arr.length];
		for(int i = 0; i < arr.length; i++)
			rev [i] = arr[x--];
			return rev;
	}
	public static int factorial (int n)
	{
	      int fact = 1;
	      int i = 1;
	      while(i <= n) {
	         fact *= i;
	         i++;
	      }
	      return fact;
	}
	public static int comb (int n , int r)
	{
		int comb;
		comb = factorial(n) / (factorial(r) * factorial(n-r));
		return comb;
	}
	public static double [][][] put_in_tabular (int [] all_terms , int num_vars)
	{
		double [][][] tabular = new double [num_vars + 1][(int) Math.pow(all_terms.length , 2)][num_vars + 1];
		for(int i = 0; i < num_vars + 1; i++)
		{
			for(int j = 0; j < (int) Math.pow(all_terms.length , 2); j++)
			{
				for(int k = 0; k < num_vars; k++)
				{					
					tabular [i][j][k] = -1;
				}
			}
		}
		for(int i = 0; i < num_vars + 1; i++)
		{
			for(int j = 0; j < (int) Math.pow(all_terms.length , 2); j++)
			{
				tabular [i][j][num_vars] = 2;
			}
		}
		for(int i = 0; i < all_terms.length; i++)
		{
			if (all_terms[i] != -1 && all_terms[i] < Math.pow(2,num_vars))
			{
				for(int j = 0; j < (int) Math.pow(all_terms.length , 2); j++)
				{
					if(degree(all_terms[i]) <= num_vars)
					{
						if(tabular [degree(all_terms[i])][j][0] == -1.0 && all_terms[i] != -1.0)
						{
							System.arraycopy(convertToBinary(all_terms[i] , num_vars), 0, tabular [degree(all_terms[i])][j], 0, num_vars);
							break;
						}
		
					
					}
				}
			}
		}
		return tabular;
	}
	public static double [][] p_i (double tabular [][][] , int num_vars , int [] all_terms)
	{
		int x ;
		double [][] p_i = new double [all_terms.length][num_vars];
		for(int i = 0; i < all_terms.length; i++)
		{
			for(int j = 0; j < num_vars; j++)
			{
				p_i [i][j] = -1;
			}
		}
		double [][][] re_tabular = new double [num_vars + 1][(int)Math.pow(all_terms.length , 2)][num_vars + 1];
		fill(all_terms , num_vars , re_tabular);
		while (one_valid(tabular))
		{
			for(int i = 0; i < tabular.length - 1; i++)
			{
				x = 0;
				for(int j = 0; j < tabular[0].length; j++)
				{
					for(int k = 0; k < tabular[0].length; k++)
					{
						if(valid(tabular[i][j] , tabular[i+1][k]) && tabular[i][j][0] != -1 && tabular[i+1][k][0] != -1)
						{
							System.arraycopy(change_bits(tabular[i][j] , tabular[i+1][k] , num_vars) , 0 , re_tabular [i][x++] , 0 , num_vars + 1);
							tabular[i][j][num_vars] = 3;
							tabular[i+1][k][num_vars] = 3;
							del_dup(re_tabular [i]);
						}
					}
				}
			}
			for(int i = 0; i < tabular.length - 1; i++)
			{
				for(int j = 0; j < tabular[0].length; j++)
				{
					if(tabular [i][j][num_vars] == 2 && tabular [i][j][0] != -1)
					{
						add_to_pi(tabular [i][j] , p_i);
					}
				}
			}
			for(int i = 0; i < tabular.length; i++)
			{
				for(int j = 0; j < tabular[0].length; j++)
				{
						System.arraycopy(re_tabular[i][j], 0, tabular[i][j], 0, num_vars);
						tabular[i][j][num_vars] = 2;
				}
			}
			fill(all_terms , num_vars , re_tabular);

		}
		for(int i = 0; i < tabular.length - 1; i++)
		{
			for(int j = 0; j < tabular[0].length; j++)
			{
				if(tabular [i][j][num_vars] == 2 && tabular [i][j][0] != -1)
				{
					add_to_pi(tabular [i][j] , p_i);
				}
			}
		}
		return p_i;
	}
	public static boolean valid (double [] arr1 , double [] arr2)
	{
		int x = 0;
		for(int i = 0; i < arr1.length - 1; i++)
		{
			if(arr1[i] != arr2[i])
				x++ ;
		}
		if (x == 1)
			return true ;
		else
			return false;
	}
	public static double [] change_bits (double [] arr1 , double [] arr2 , int num_vars)
	{
		double [] temp = new double [arr1.length];
		for(int i = 0; i < arr1.length - 1; i++)
		{
			if(arr1[i] != arr2[i])
			{
				for(int j = 0; j < arr1.length; j++)
				{
					temp [j] = arr1 [j];
					temp[i] = -2;
				}
				temp [arr1.length - 1] = 2;
				break ;
			}
		}
		return temp;
	}
	public static boolean one_valid (double tabular [][][])
	{
		boolean x = false;
		for(int i = 0; i < tabular.length - 1; i++)
		{
			for(int j = 0; j < tabular[0].length; j++ )
			{
				for(int k = 0; k < tabular[0].length; k++)
				{
					if(valid(tabular[i][j] , tabular[i+1][k]) && tabular[i][j][0] != -1 && tabular[i+1][k][0] != -1)
					{
						return true;
					}
				}
			}
		}
		return x;
	}
	public static void add_to_pi (double arr [] , double [][] p_i)
	{
		for(int i = 0; i < p_i.length; i++)
		{
			if(p_i[i][0] == -1)
			{
				System.arraycopy(arr, 0, p_i[i], 0, p_i[0].length);
				break;
			}
		}
	}
	public static void del_dup (double re_tabular [][])
	{
		for(int i = 1; i < re_tabular.length; i++)
		{
			for(int j = 0; j < i; j++)
			{
				if(same(re_tabular[i] , re_tabular[j]))
				{
					for(int k = 0; k < re_tabular[i].length - 1; k++)
						re_tabular[i][k] = -1;
				}
			}
		}
	}
	public static boolean same (double [] arr1 , double [] arr2)
	{
		int x = 0;
		for(int i = 0; i < arr1.length - 1; i++)
		{
			if(arr1[i] != arr2[i])
				x++ ;
		}
		if (x == 0)
			return true ;
		else
			return false;
	}
	public static void fill(int [] all_terms , int num_vars , double [][][] re_tabular)
	{
		for(int i = 0; i < num_vars + 1; i++)
		{
			for(int j = 0; j < (int) Math.pow(all_terms.length , 2); j++)
			{
				for(int k = 0; k < num_vars; k++)
				{					
					re_tabular [i][j][k] = -1;
				}
			}
		}
		for(int i = 0; i < num_vars + 1; i++)
		{
			for(int j = 0; j < (int) Math.pow(all_terms.length , 2); j++)
			{
				re_tabular [i][j][num_vars] = 2;
			}
		}
	}
	public static void print_pi (double [][] p_i)
	{
		int y = 0;
		for(int i = 0 ; i < p_i.length; i++)
		{
			if(i != 0 && p_i[i][0] != -1)
				System.out.print(" , ");
			if(p_i[i][0] != -1)
			{
				for(int j = 0; j < p_i[0].length; j++)
				{
					int x = 65 + j;
					if(p_i[i][j] == 1)
						System.out.print((char)x);
					if(p_i[i][j] == 0)
						System.out.print(((char)x) + "'");
				}
				y++ ;
			}
			else;
		}
		if (y == 0)
			System.out.print("does not exist");
		System.out.println();
	}
	public static double [][][] pi_table (double [][] p_i , int [] all_terms , int num_vars)
	{
		double [][][] pi_table = new double [p_i.length + 1][all_terms.length + 1][num_vars] ;
		for(int i = 0; i <= p_i.length; i++)
		{
			for(int j = 0; j <= all_terms.length; j++)
			{
				for(int k = 0; k < num_vars; k++)
				{
					pi_table [i][j][k] = -1;
				}
			}
		}
		for(int i = 0; i < p_i.length; i++)
		{
			pi_table [i + 1][0] = p_i [i];
		}
		for(int i = 0; i < all_terms.length; i++)
		{
			if(all_terms [i] < Math.pow(2, num_vars))
				System.arraycopy(convertToBinary(all_terms [i] , num_vars), 0, pi_table [0][i + 1], 0, num_vars);
		}
		pi_table = pi_cover(pi_table);
		return pi_table ;
	}
	public static double [][][] pi_cover (double [][][] pi_table)
	{
		double [][][] pi_cover = new double [pi_table.length][pi_table[0].length][pi_table[0][0].length];
		pi_cover = pi_table;
		for(int i = 1; i < pi_table.length; i++)
		{
			for(int j = 1; j < pi_table[0].length; j++)
			{
				if(cover_valid(pi_cover[i][0] , pi_cover[0][j]) && pi_cover[i][0][0] != -1 && pi_cover[0][j][0] != -1)
					pi_cover[i][j][0] = 2;
			}
		}
		return pi_cover;
	}
	public static boolean cover_valid (double arr1[] , double arr2 [])
	{
		for(int i = 0; i< arr1.length; i++)
		{
			if(arr1[i] == -2);
			else
				if(arr1 [i] != arr2[i])
					return false;
		}
		return true;
	}
	public static double [][] ess_pi (double [][][] pi_table , int num_vars)
	{
		double [][] ess = new double [pi_table.length - 1][num_vars];
		for(int i = 0; i < pi_table.length - 1; i++)
		{
			for(int j = 0; j < num_vars; j++)
			{
				ess [i][j] = -1;
			}
		}
		int x , y = 0 , z = 0;
		for(int j = 1; j < pi_table[0].length; j++)
		{
			x = 0;
			for(int i = 0; i < pi_table.length; i++)
			{
				if (pi_table[i][j][0] == 2)
				{
					x++ ;
					z = i;
				}
			}
			if(x == 1)
				System.arraycopy(pi_table[z][0], 0, ess[y++], 0, num_vars);
		}
		return ess;
	}
	public static double [][][] petrick_table (double [][][] pi_table, double [][] p_i , int [] all_terms , int num_vars , double [][] ess)
	{
		double [][][] re_pi_table = new double [p_i.length + 1][all_terms.length + 1][num_vars] ;
		re_pi_table = pi_table;
		for(int i = 1; i < re_pi_table[0].length; i++)
		{
			if(in_ess(ess , re_pi_table[i][0]))
			{
				re_pi_table[i][0][0] = -1;
				for(int j = 1; j < re_pi_table[0].length; j++)
				{
					if(re_pi_table[i][j][0] == 2)
					{
						for(int k = 0; k < re_pi_table.length; k++)
						{
							re_pi_table[k][j][0] = -1;
						}
					}
				}
			}
		}
		return re_pi_table;
	}
	public static boolean in_ess (double [][] ess , double [] arr)
	{
		int x;
		for(int j = 0; j < ess.length; j++)
		{
			x = 0;
			for(int i = 0; i < ess[0].length; i++)
			{
				if(ess[j][i] != arr[i])
					x++ ;
			}
			if (x == 0)
				return true ;
		}
		return false;
	}
	public static double [][] min (double [][][] petrick_table , double [][] ess)
	{
		double [][] min;
		double [][] arr = new double [2][col(petrick_table) - 1];
		int x , y = 0;
		for(int j = 0; j < petrick_table[0].length; j++)
		{
			x = 0;
			if(petrick_table[0][j][0] != -1)
			{
				for(int i = 1; i < petrick_table.length; i++)
				{
					if(petrick_table[i][j][0] == 2)
					{
						x++ ;
					}
				}
				arr[0][y] = j;
				arr[1][y] = x;
				y++ ;
			}
		}
		min = fill_min(arr , petrick_table , ess);
		return min;
	}
	public static int row (double [][][] petrick_table)
	{
		int x = 1 , y;
		for(int j = 1; j < petrick_table[0].length; j++)
		{
			y = 0;
			for(int i = 1; i < petrick_table.length; i++)
			{
				if(petrick_table[0][j][0] == -1)
					break;
				else
				{
					if(petrick_table[i][j][0] == 2)
						y++ ;
				}
			}
			if(y != 0)
				x *= y;
		}
		return x;
	}
	public static int col (double [][][] petrick_table)
	{
		int x = 1;
		for(int j = 1; j < petrick_table[0].length; j++)
		{
			if(petrick_table[0][j][0] != -1)
				x++;
		}
		return x;
	}
	public static double [][] fill_min (double [][] arr , double [][][] petrick_table , double [][] ess)
	{
		double y ;
		int z , j , k , m = 1 , arr1[] = {-1 , -1};
		double [][] min = new double [row(petrick_table)][col(petrick_table)];
		double x = row(petrick_table);
		for(int i = 0; i < arr[0].length; i++)
		{
			x = x / arr[1][i];
			z = 0;
			k = 1;
			j = 0;
			y = -1;
			while(z < min.length && j < min.length) 
			{
				arr1 = bring_pi(y , petrick_table , m);
						y = arr1[0];
				for(int l =  0; l < x && j < min.length; j++ , l++)
				{
					min[j][i] = y;
					z++ ;
				}
				if(k == arr[1][i] && j != min.length)
				{
					y = -1;
					k = 1;
				}
				else
					k++ ;
			}
			m = arr1[1] + 1;
		}
		del_dups(min);
		cost(min , petrick_table , ess);
		return min;
	}
	public static int [] bring_pi (double x , double [][][] petrick_table , int j)
	{
		int arr[] = {-1 , -1};
		for(int i = 1; i < petrick_table.length; i++)
		{
			while(petrick_table[0][j][0] == -1)
				j++ ;
			if(petrick_table[i][j][0] != -1 && i > x)
			{
				arr[0] = i;
				arr[1] = j;
				return arr;
			}
		}
		return arr;
	}
	public static void del_dups (double [][] min)
	{
		for(int i = 0; i < min.length; i++)
		{
			for(int j = 1; j < min[0].length; j++)
			{
				for(int k = 0; k < j; k++)
				{
					if(min[i][j] == min[i][k])
					{
						min[i][j] = -1;
						break;
					}
				}
			}
		}
	}
	public static void cost (double [][] min , double [][][] petrick_table , double [][] ess)
	{
		for(int i = 0; i < min.length; i++)
		{
			for(int j = 0; j < min[0].length - 1; j++)
			{
				if(min[i][j] != -1)
					min[i][min[0].length - 1] += cost_array(petrick_table[(int) min[i][j]][0] , ess);
			}
		}
	}
	public static double cost_array (double [] arr , double [][] ess)
	{
		int cost = 0;
		if(num_digits(arr) == 0)
			return 0;
		if(num_digits(arr) > 1)
			cost ++;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] == 0)
			{
				if(comp_in_ess(i , ess))
					cost ++;
				else
					cost += 2;
			}
			else if(arr[i] == 1)
				cost++ ;
			else ;
		}
		return cost;
		
	}
	public static double num_digits (double [] arr)
	{
		int x = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] >= 0)
				x++ ;
		}
		return x;
	}
	public static boolean comp_in_ess (int j , double [][] ess)
	{
		for(int i = 0; i < ess.length; i++)
		{
			if(ess[i][j] == 0)
				return true;
		}
		return false;
	}
	public static int find_min (double [][] min)
	{
		double minn = min[0][min[0].length - 1];
		int x = 0;
		for(int i = 1; i < min.length; i++)
		{
			if(min[i][min[0].length - 1] < minn)
			{
				minn = min[i][min[0].length - 1];
				x = i;
			}
		}
		return x;
	}
	public static double [][] put_in_min_form (double [][] min , double [][] ess , double [][][] petrick_table)
	{
		int x = find_min(min);
		double min_form [][] = new double [ess.length + (int) num_digits(min[x]) - 1][ess[0].length];
		for(int i = 0; i < ess.length; i++)
		{
			min_form[i] = ess[i];
		}
		int y = ess.length;
		for(int i = 0; i < min[0].length - 1; i++)
		{
			if(min[x][i] != -1)
			{
				min_form[y++] = petrick_table[(int) min[x][i]][0];
			}
		}
		return min_form;
	}
	public static void print_min (double [][] p_i)
	{
		int y = 0;
		for(int i = 0 ; i < p_i.length; i++)
		{
			if(i != 0 && p_i[i][0] != -1 && y != 0)
				System.out.print(" + ");
			if(p_i[i][0] != -1)
			{
				for(int j = 0; j < p_i[0].length; j++)
				{
					int x = 65 + j;
					if(p_i[i][j] == 1)
						System.out.print((char)x);
					if(p_i[i][j] == 0)
						System.out.print(((char)x) + "'");
				}
				y++ ;
			}
			else;
		}
	}
	public static double total_cost (double [][] min_form)
	{
		double total_cost = 0;
		for(int i = 0; i < min_form.length; i++)
		{
			if(min_form[i][0] != -1)
			{
				if(num_digits(min_form[i]) > 1)
					total_cost ++;
				for(int j = 0; j < min_form[0].length; j++)
				{
					if(min_form[i][j] == 1)
						total_cost += 1;
					if(min_form[i][j] == 0)
						if(!in_prev(min_form , i , j))
							total_cost += 2;
						else
							total_cost += 1;
				}
			}
		}
		if(min_vars(min_form) == 1)
			total_cost --;
		return total_cost;
	}
	public static boolean in_prev(double [][] min_form , int i , int j)
	{
		for(int k = 0; k < i; k++)
		{
			if(min_form[i][j] == min_form[k][j])
				return true ;
		}
		return false;
	}
	public static double min_vars (double [][] min_form)
	{
		double x = 0;
		for(int i = 0; i < min_form.length; i++)
		{
			if(min_form [i][0] != -1)
				x++;
		}
		return x;
	}
}