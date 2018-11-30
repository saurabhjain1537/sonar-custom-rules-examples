package org.sonar.samples.java.checks;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;

import com.google.common.collect.ImmutableList;

@Rule( key = "JVSTableDestroy",
name = "JVS Table objects should be destroyed",
description = "JVS Table objects should be destroyed",
tags = {"bug"})
public class JVSTableDestroy extends IssuableSubscriptionVisitor {

	List<String> jvsTableVariables = new ArrayList<>();
	List<String> jvsTableDestroyCalls = new ArrayList<>();
	
	@Override
	public List<Kind> nodesToVisit() {
	  return ImmutableList.of(Kind.VARIABLE, Kind.METHOD_INVOCATION);
	}
	
	@Override
	public void visitNode(Tree tree) {
		
		if(tree.is(Kind.VARIABLE)) {

			VariableTree variable = (VariableTree) tree; 
			System.out.println("\nvariable " + variable.simpleName());
			//System.out.println("\nvariable " + variable.);
			System.out.println("symbol " + variable.symbol().type().fullyQualifiedName());
			
			if("com.olf.openjvs.Table".equalsIgnoreCase(variable.symbol().type().fullyQualifiedName())) {
				jvsTableVariables.add(variable.simpleName().name());
			}
		
		} else if (tree.is(Kind.IDENTIFIER)) {
			
			IdentifierTree identifier = (IdentifierTree) tree;
			System.out.println("\nidentifier " + identifier.name());

		} else if (tree.is(Kind.ASSIGNMENT)) {

			AssignmentExpressionTree assignment = (AssignmentExpressionTree)tree;

			System.out.println("Data type " + assignment.variable().symbolType().fullyQualifiedName());
			System.out.println("firstToken " + assignment.firstToken().text());
			System.out.println("lastToken " + assignment.lastToken().text());

		} else if (tree.is(Kind.METHOD_INVOCATION)) {
			
			MethodInvocationTree invocation = (MethodInvocationTree) tree;
			System.out.println("\ninvocation " + invocation.symbol().name());
			System.out.println("invocation firstToken " + invocation.firstToken().text());
			System.out.println("invocation symbolType " + invocation.symbolType().name());
			
			if("destroy".equalsIgnoreCase(invocation.symbol().name()) ) {
				jvsTableDestroyCalls.add(invocation.firstToken().text());
			}
		}
		
		System.out.println("\njvsTableVariables " + jvsTableVariables);
		System.out.println("jvsTableDestroyCalls " + jvsTableDestroyCalls);
	}
}
