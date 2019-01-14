import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

//Preyansh Kaushik ID: 260790402

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf; //true if the node is a leaf
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf



		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {
			DTNode newsplitnode = new DTNode();

			//YOUR CODE HERE

	if(datalist.size() >= minSizeDatalist) {
		boolean allLabel = true;
		int onelabel = datalist.get(0).y;
		for(Datum datum : datalist) {
			if(datum.y != onelabel) {
				allLabel = false;
				break;
			}
		}
		if(allLabel == true) {
			DTNode newleaf = new DTNode();
			newleaf.leaf = true;
			newleaf.label = datalist.get(0).y;
			return newleaf;
		}
		else {
			//create attribute test question through some other code
			double bestavgentropy = (double) Double.POSITIVE_INFINITY; 
			int best_attribute = -1;
			double best_threshold = -1;
			
			ArrayList<Datum> leftsplit = new ArrayList<Datum>();
			ArrayList<Datum> rightsplit = new ArrayList<Datum>();
			//go through x1 position 0 of double[] x
			for(int i = 0; i<=1; i++) {
				//cycle through the datalist
				for(Datum datum : datalist) {
					double thresholdrn = datum.x[i];
					//clear both arraylists since doing this again
					leftsplit.clear();
					rightsplit.clear();
					
					for (Datum datum2 : datalist) {
						if(datum2.x[i] < thresholdrn) {
							//if its less than the threshold rn put all to the leftsplit
							leftsplit.add(datum2);
						}
						else {
							rightsplit.add(datum2);
						}
					}
				
					double leftsize = (double) leftsplit.size();
					double rightsize = (double) rightsplit.size();
					double datalistsize = (double) datalist.size();
					
					double avgentropy = ((leftsize/datalistsize)*calcEntropy(leftsplit)) + ((rightsize/datalistsize)*calcEntropy(rightsplit));
					
					if(bestavgentropy > avgentropy) {
						bestavgentropy = avgentropy;
						best_attribute = i;
						best_threshold = thresholdrn;

					}
				}
			}
			
			
			//attribute and threshold have been found, now store those
			newsplitnode.leaf = false;
			newsplitnode.attribute = best_attribute;
			newsplitnode.threshold = best_threshold; 
			
			//split into d1 and d2
			ArrayList<Datum> D1 = new ArrayList<Datum>();
			ArrayList<Datum> D2 = new ArrayList<Datum>();
			for(Datum data : datalist) {
				if(data.x[best_attribute] < best_threshold) {
					D1.add(data);
			}
				else {
					D2.add(data);
				}}

			
			newsplitnode.left = fillDTNode(D1);
			newsplitnode.right = fillDTNode(D2);
			}	
			
		
		
		
		}
	else {

		newsplitnode.leaf = true;
		newsplitnode.label = findMajority(datalist);
		
	}


	return newsplitnode;
		

		}

		
		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		
			int classifyAtNode(double[] xQuery) {
				if(this.leaf == true) {
					return this.label;
				}
				else {
					if(xQuery[this.attribute] >= this.threshold) {
						return (this.right.classifyAtNode(xQuery));
					}
					else {
						return (this.left.classifyAtNode(xQuery));
					}
				}
		}
	    
		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter

	    
		public boolean equals(Object dt2) {
			//typecast object into DTNode to override
			DTNode inputnode = (DTNode) dt2;
			
			//base cases: 
				if (inputnode == null) {
					return false;}

		
		//if this is a leaf, check cases where input is a leaf or not.		
		if(this.leaf == true) {
			if(inputnode.leaf == false) {
				return false;
			}
			else if(inputnode.label != this.label) {
				return false;
			}
			else {
				
				return true;
			}
		}
		else {
			if(inputnode.leaf == true) {
				return false;
		}
			if(this.attribute != inputnode.attribute || this.threshold != inputnode.threshold) {
				return false;
			}
		}
		
		boolean leftequals = false;
		boolean rightequals = false;

		//recursive on left and right
		if(this.left == null) {
			if(inputnode.left == null) {
				leftequals = true;
			}
			else {
				return false;
			}
		}
		else {
			leftequals = left.equals(inputnode.left);
		}
		
		if(right== null) {
			if(inputnode.right == null) {
				rightequals = true;
			}
			else {
				return false;
			}
		}
		else {
			rightequals = right.equals(inputnode.right);
		}
		

			return (rightequals && leftequals);
		}
	}

	//Given a dataset, this retuns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}


}
