import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Tester {
	
	public static void main(String args[]) throws IOException
	{		
		/*
		//generating and writing the 3SAT instances
		File file = new File("threeSATResults.txt");	
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		
		double runtimeDP[] = new double[100];
		double runtimePseudo[] = new double[100];
		
		//3SAT random instances
		for(int i = 0; i < 100; i++)
		{
			ArrayList<Literal> lits = new ArrayList<Literal>();
			ArrayList<ThreeSATClause> clause = new ArrayList<ThreeSATClause>();
			
			for(int j = 0; j < 10 + (int) Math.floor(Math.random() * 21); j++)
			{
				Literal l1=null,l2=null,l3=null;
				for(int k = 0; k < 3; k++)
				{
					int index = (k + 1) + (int)(Math.round(Math.random()) + 1) * (int) (j / 5);
					if(Math.round(Math.random()) == 0)
					{
						if(k == 0)
						{
							l1 = new Literal("x" + index, false);
						}
						else if(k == 1)
						{
							l2 = new Literal("x" + index, false);
						}
						else
						{
							l3 = new Literal("x" + index, false);
						}
					}
					else
					{
						if(k == 0)
						{
							l1 = new Literal("x" + index + "Not", true);
						}
						else if(k == 1)
						{
							l2 = new Literal("x" + index + "Not", true);
						}
						else
						{
							l3 = new Literal("x" + index + "Not", true);
						}
					}
				}
				lits.add(l1);
				lits.add(l2);
				lits.add(l3);
				clause.add(new ThreeSATClause(l1,l2,l3));
			}
			Collections.sort(lits);
			ThreeSAT threeSATInstance = new ThreeSAT(lits, clause);
			
			writer.write("INSTANCE " + (i + 1) + " OF 3SAT:");
			writer.newLine();
			writer.write(threeSATInstance.toString());
			writer.newLine();
			writer.newLine();
			
			double startTime, endTime;
			
			startTime = System.currentTimeMillis();
			threeSATInstance.useDynamicProgrammingNW();
			endTime = System.currentTimeMillis();
			runtimeDP[i] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			threeSATInstance.usePseudoPolyTime();
			endTime = System.currentTimeMillis();
			runtimePseudo[i] = endTime - startTime;
			
			writer.write("Dynamic Programming O(nW) Solution:");
			writer.newLine();
			writer.write(threeSATInstance.printTruthAssignment(threeSATInstance.useDynamicProgrammingNW()));
			writer.newLine();
			writer.newLine();
			
			writer.write("Pseudo Polynomial Time Dynamic Programming Solution:");
			writer.newLine();
			writer.write(threeSATInstance.printTruthAssignment(threeSATInstance.usePseudoPolyTime()));
			writer.newLine();
			writer.newLine();
		}
		
		Arrays.sort(runtimeDP);
		Arrays.sort(runtimePseudo);
		
		double averageRTDP=0, averageRTPseudo=0;
		
		writer.write("Runtimes for O(nW) Dynamic Programming");
		writer.newLine();
		for(double d : runtimeDP)
		{
			averageRTDP += d;
		}
		averageRTDP /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageRTDP));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(runtimeDP[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(runtimeDP[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(runtimeDP[99]));
		writer.newLine();
		writer.newLine();
		
		writer.write("Runtimes for Pseudo Polynomial Time Dynamic Programming");
		writer.newLine();
		for(double d : runtimePseudo)
		{
			averageRTPseudo += d;
		}
		averageRTPseudo /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageRTPseudo));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(runtimePseudo[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(runtimePseudo[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(runtimePseudo[99]));
		writer.newLine();
		writer.newLine();
		
		writer.close();
		*/
		
		/*
		double qualityGreedy[] = new double[100];
		double qualityFPTAS[] = new double[100];
		double runtimeDP[] = new double[100];
		double runtimePseudo[] = new double[100];
		double runtimeGreedy[] = new double[100];
		double runtimeFPTAS[] = new double[100];
		
		File file = new File("maxKnapResults.txt");	
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
		
		//maxKnap random instances
		for(int i = 0; i < 100; i++)
		{
			ArrayList<Item> items = new ArrayList<Item>();
			int maxBudget = (int) Math.floor(Math.random() * 501);
			
			for(int j = 0; j < 30 + (int) Math.floor(Math.random() * 21); j++)
			{
				Item it = new Item(Math.floor(Math.random() * 26), Math.floor(Math.random() * 26), j);
				items.add(it);
			}
			
			Knapsack itemList = new Knapsack(items);
			MaxKnapsack maxKnap = new MaxKnapsack(maxBudget, itemList);
			
			writer.write("INSTANCE " + (i + 1) + " OF 0-1 KNAPSACK:");
			writer.newLine();
			writer.write("Max Budget: ");
			writer.newLine();
			writer.write(Integer.toString(maxBudget));
			writer.newLine();
			writer.write("Item List:");
			writer.newLine();
			writer.write(itemList.toString());
			writer.newLine();
			
			writer.write("O(nW) Dynamic Programming:");
			writer.newLine();
			writer.write(maxKnap.dynamicProgrammingNW(itemList).toString());
			writer.newLine();
			
			writer.write("Pseudo Polynomial Time Dynamic Programming:");
			writer.newLine();
			writer.write(maxKnap.psuedoPolyTimeDynamicProgramming(itemList).toString());
			writer.newLine();	
			
			writer.write("Greedy 2-Approximation:");
			writer.newLine();
			writer.write(maxKnap.greedyAlgorithm(itemList).toString());
			writer.newLine();	
			
			writer.write("FPTAS Dynamic Programming (with error tolerance of 0.05):");
			writer.newLine();
			writer.write(maxKnap.fullyPolynomialTimeApproximation(itemList, 0.05).toString());
			writer.newLine();	
			
			writer.newLine();	
			
			double optimal = maxKnap.dynamicProgrammingNW(itemList).totalValue();
			qualityGreedy[i] = maxKnap.greedyAlgorithm(itemList).totalValue()/optimal;
			qualityFPTAS[i] = maxKnap.fullyPolynomialTimeApproximation(itemList, 0.05).totalValue()/optimal;
			
			double startTime, endTime;
			
			startTime = System.currentTimeMillis();
			maxKnap.dynamicProgrammingNW(itemList);
			endTime = System.currentTimeMillis();
			runtimeDP[i] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			maxKnap.psuedoPolyTimeDynamicProgramming(itemList);
			endTime = System.currentTimeMillis();
			runtimePseudo[i] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			maxKnap.greedyAlgorithm(itemList);
			endTime = System.currentTimeMillis();
			runtimeGreedy[i] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			maxKnap.fullyPolynomialTimeApproximation(itemList, 0.05);
			endTime = System.currentTimeMillis();
			runtimeFPTAS[i] = endTime - startTime;
		}
		Arrays.sort(qualityGreedy);
		Arrays.sort(qualityFPTAS);
		Arrays.sort(runtimeDP);
		Arrays.sort(runtimePseudo);
		Arrays.sort(runtimeGreedy);
		Arrays.sort(runtimeFPTAS);
		
		writer.write("Quality of Greedy 2-Approximation:");
		writer.newLine();
		double averageGreedy = 0;
		double averageFPTAS = 0;
		for(double d : qualityGreedy)
		{
			averageGreedy += d;
		}
		averageGreedy /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageGreedy));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(qualityGreedy[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(qualityGreedy[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(qualityGreedy[99]));
		writer.newLine();
		writer.newLine();
		
		writer.write("Quality of FPTAS with error tolerance of 0.05:");
		writer.newLine();
		for(double d : qualityFPTAS)
		{
			averageFPTAS += d;
		}
		averageFPTAS /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageFPTAS));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(qualityFPTAS[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(qualityFPTAS[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(qualityFPTAS[99]));
		writer.newLine();
		
		writer.newLine();
		double averageRTDP=0,averageRTPseudo=0,averageRTGreedy=0,averageRTFPTAS=0;
		
		writer.write("Runtimes for O(nW) Dynamic Programming");
		writer.newLine();
		for(double d : runtimeDP)
		{
			averageRTDP += d;
		}
		averageRTDP /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageRTDP));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(runtimeDP[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(runtimeDP[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(runtimeDP[99]));
		writer.newLine();
		writer.newLine();
		
		writer.write("Runtimes for Pseudo Polynomial Time Dynamic Programming");
		writer.newLine();
		for(double d : runtimePseudo)
		{
			averageRTPseudo += d;
		}
		averageRTPseudo /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageRTPseudo));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(runtimePseudo[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(runtimePseudo[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(runtimePseudo[99]));
		writer.newLine();
		writer.newLine();
		
		writer.write("Runtimes for Greedy 2-Approximation");
		writer.newLine();
		for(double d : runtimeGreedy)
		{
			averageRTGreedy += d;
		}
		averageRTGreedy /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageRTGreedy));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(runtimeGreedy[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(runtimeGreedy[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(runtimeGreedy[99]));
		writer.newLine();
		writer.newLine();
		
		writer.write("Runtimes for FPTAS with error tolerance of 0.05");
		writer.newLine();
		for(double d : runtimeFPTAS)
		{
			averageRTFPTAS += d;
		}
		averageRTFPTAS /= 100;
		writer.write("Average: ");
		writer.write(Double.toString(averageRTFPTAS));
		writer.newLine();
		writer.write("Median: ");
		writer.write(Double.toString(runtimeFPTAS[49]));
		writer.newLine();
		writer.write("Minimum: ");
		writer.write(Double.toString(runtimeFPTAS[0]));
		writer.newLine();
		writer.write("Maximum: ");
		writer.write(Double.toString(runtimeFPTAS[99]));
		writer.newLine();
		writer.newLine();
		
		writer.close();
		*/
		
		/*
		Literal x1,x1Not,x2,x2Not,x3,x3Not,x4,x4Not,x5,x5Not,x6,x6Not;
		x1 = new Literal("x1", false);
		x1Not = new Literal("x1Not", true);
		x2 = new Literal("x2", false);
		x2Not = new Literal("x2Not", true);
		x3 = new Literal("x3", false);
		x3Not = new Literal("x3Not", true);
		x4 = new Literal("x4", false);
		x4Not = new Literal("x4Not", true);
		x5 = new Literal("x5", false);
		x5Not = new Literal("x5Not", true);
		x6 = new Literal("x6", false);
		x6Not = new Literal("x6Not", true);
		ArrayList<Literal> allThreeSATLiterals = new ArrayList<Literal>();
		allThreeSATLiterals.add(x1);
		allThreeSATLiterals.add(x2);
		allThreeSATLiterals.add(x3);
		allThreeSATLiterals.add(x4);
		allThreeSATLiterals.add(x5);
		allThreeSATLiterals.add(x6);
		
		ThreeSATClause c1,c2,c3,c4;
		c1 = new ThreeSATClause(x1Not, x2Not, x3Not);
		c2 = new ThreeSATClause(x4Not, x2Not, x5Not);
		c3 = new ThreeSATClause(x4, x3, x6);
		c4 = new ThreeSATClause(x1Not, x2Not, x3Not);
		ArrayList<ThreeSATClause> allThreeSATClauses = new ArrayList<ThreeSATClause>();
		allThreeSATClauses.add(c1);
		allThreeSATClauses.add(c2);
		allThreeSATClauses.add(c3);
		allThreeSATClauses.add(c4);
		ThreeSAT threeSATInstance = new ThreeSAT(allThreeSATLiterals, allThreeSATClauses);
		System.out.println(threeSATInstance);
		
		System.out.println(threeSATInstance.printTruthAssignment(threeSATInstance.useDynamicProgrammingNW()));
		*/
	}
	
}
