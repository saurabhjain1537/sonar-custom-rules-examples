/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Symbol.VariableSymbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.tree.VarTypeTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;



@Rule(key = "AvoidMethodWithSameTypeInArgument")
/**
 * To use subsctiption visitor, just extend the IssuableSubscriptionVisitor.
 */
public class MyCustomSubscriptionRule extends IssuableSubscriptionVisitor {

  @Override
  public List<Tree.Kind> nodesToVisit() {
    // Register to the kind of nodes you want to be called upon visit.

    return ImmutableList.of(Tree.Kind.ASSIGNMENT);
  }
  
  

  @Override
  public void visitNode(Tree tree) {
    // Cast the node to the correct type :
    // in this case we registered only to one kind so we will only receive MethodTree see Tree.Kind enum to know about which type you can
    // cast depending on Kind.
   // MethodTree methodTree = (MethodTree) tree;
/*	  VariableTree varTree = (VariableTree) tree;
	  if(varTree.simpleName().symbol().isMethodSymbol()) {
		  System.out.println("Method Symbol " + varTree.simpleName().symbol().name());
	  }else if (varTree.simpleName().symbol().isVariableSymbol()) {
		  System.out.println("Variable Symbol " + varTree.simpleName().symbol().name());
	  }
	 System.out.println("Variable Name " + varTree.simpleName().name());
	 System.out.println(" Identifier Token " + varTree.simpleName().identifierToken().text());
    System.out.println("\n ======== visitNode Variable Tree 01 : " + varTree.symbol().name() +" "+ varTree.symbol().type().name());
    if(varTree.symbol().type().isSubtypeOf("java.lang.String")) {
    	System.out.println("BSNL Chauka Connecting India");
    	 reportIssue(tree, "message");
    }*/
	
	  System.out.println("\n ==================== I am called");
	  AssignmentExpressionTree assignTree = (AssignmentExpressionTree) tree;
	  System.out.println("Assignment Tree Variable " + assignTree.variable());
	  System.out.println("Assignment Tree Variable Name " + assignTree.variable().symbolType().name());
	  System.out.println("Assignment Tree Expressoin " + assignTree.expression().toString());
	  System.out.println("Assignment Tree Variable " + assignTree.expression().symbolType().name());
	  if(assignTree.variable().symbolType().name().equalsIgnoreCase("string")) {
		  reportIssue(tree, "message");  
	  }
	  
	 
 /*   if (varTree.symbol().isVariableSymbol())
    {
        VariableSymbol varSymbol = (VariableSymbol)varTree.symbolType();
        System.out.println("\n ============ Variable is of type : " + varSymbol.name() 
                        +" "+ varSymbol.type().name()
                        +" "+ varSymbol.type().isSubtypeOf("java.lang.Object"));
        if( varSymbol.type().isSubtypeOf("java.lang.Object"))
        reportIssue(tree, "message");
       
    }else 
    {
    	System.out.println("No Variable defined in the method");
    }*/
    // Retrieve symbol of method.
   /* MethodSymbol methodSymbol = methodTree.symbol();
    
    
    Type returnType = methodSymbol.returnType().type();
    // Check method has only one argument.
    if (methodSymbol.parameterTypes().size() == 1) {
      Type argType = methodSymbol.parameterTypes().get(0);
      // Verify argument type is same as return type.
      if (argType.is(returnType.fullyQualifiedName())) {
        // raise an issue on this node of the SyntaxTree
        reportIssue(tree, "message");
      }
    }*/
  }
}
