import java.util.Scanner;
public class main
{
	public static void main(String args[])
	{
		Scanner scan_var = new Scanner(System.in);
		int temp ;
		System.out.println("To start choose : 1");
		temp = scan_var.nextInt();
		while(temp == 1)
		{
			int num_vars ;
			System.out.println();
			do
			{
				System.out.println("Enter the number of variables (maximum is 26)");
				num_vars = scan_var.nextInt();
			}
			while(num_vars < 1 || num_vars > 26);
			System.out.println("Enter minterms (a negative number to end input)");
			int min_terms[];
			min_terms = methods.scan(num_vars) ;
			System.out.println("Enter don't care terms (a negative number to end input)");
			int don_care_terms[];
			don_care_terms = methods.scan(num_vars);
			int all_terms[];
			all_terms = methods.merge(min_terms, don_care_terms);
			if(all_terms.length == 0)
			{
				System.out.println("Minimal Form is");
				System.out.println("0");
				return;
			}
			double[][][] tabular;
			tabular = methods.put_in_tabular(all_terms, num_vars);
			double p_i [][];
			p_i = methods.p_i(tabular , num_vars , all_terms);
			System.out.println();
			System.out.println("Prime Implicants are :");
			methods.print_pi(p_i);
			double [][][] pi_table = new double [p_i.length + 1][all_terms.length + 1][num_vars] ;
			pi_table = methods.pi_table (p_i , min_terms , num_vars);
			double ess [][];
			ess = methods.ess_pi(pi_table, num_vars);
			methods.del_dup(ess);
			System.out.println();
			System.out.println("Essential Prime Implicants are :");
			methods.print_pi(ess);
			double [][][] petrick_table;
			petrick_table = methods.petrick_table(pi_table, p_i, all_terms, num_vars, ess);
			double min [][];
			min = methods.min(petrick_table , ess);
			double min_form [][];
			min_form = methods.put_in_min_form(min , ess, petrick_table);
			System.out.println();
			System.out.println("Minimal Form is");
			if(all_terms.length == Math.pow(2, num_vars))
				System.out.println("1");
			else
				methods.print_min(min_form);
			System.out.println();
			System.out.println();
			System.out.println("Total Cost is " + methods.total_cost(min_form));
			System.out.println();
			System.out.println("For another process choose : 1");
			System.out.println("To End choose : 2");
			temp = scan_var.nextInt();
		}
	}
}
