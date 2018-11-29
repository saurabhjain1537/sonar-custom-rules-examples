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

import java.util.Iterator;
import java.util.List;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.ModifierTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.tree.TypeParameterTree;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.MethodReferenceTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import com.olf.openjvs.Table;

@Rule(key = "AvoidAnnotation")
public class MyCustomSubscriptionRule2 extends BaseTreeVisitor implements JavaFileScanner {

  private static final String DEFAULT_VALUE = "Inject";

  private JavaFileScannerContext context;

  /**
   * Name of the annotation to avoid. Value can be set by users in Quality profiles.
   * The key
   */
  @RuleProperty(
    defaultValue = DEFAULT_VALUE,
    description = "Name of the annotation to avoid, without the prefix @, for instance 'Override'")
  protected String name;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;

    scan(context.getTree());

    System.out.println(PrinterVisitor.print(context.getTree()));
  }
  
  @Override

	public void visitVariable(VariableTree tree) {
		// TODO Auto-generated method stub
	  

		super.visitVariable(tree);
	}
  
  @Override
	public void visitMethodInvocation(MethodInvocationTree tree) {
	
	  MethodInvocationTree methodTree = (MethodInvocationTree) tree;
	  System.out.println("\n======= Method Invocation Tree======="+ methodTree.arguments());
	  
	  Iterator it = methodTree.arguments().iterator();
	  while(it.hasNext()) {  
		  System.out.println("\n=======Method Invocation Tree iteration=======" + it.next());  
	  }
	  System.out.println("\n=======Method Invocation Tree01======="+ methodTree.methodSelect());
	  // TODO Auto-generated method stub
		super.visitMethodInvocation(tree);
	}
  
  @Override
	public void visitMethodReference(MethodReferenceTree tree) {
	  MethodReferenceTree methodrefTRee = (MethodReferenceTree) tree;
	  System.out.println("\n=======Method Reference Tree=======" + methodrefTRee.method());
	  System.out.println("\n=======Method Reference Tree01=======" + methodrefTRee.expression());
	  // TODO Auto-generated method stub
		super.visitMethodReference(tree);
	}

  @Override
	public void visitAssignmentExpression(AssignmentExpressionTree tree) {
		
	  AssignmentExpressionTree assignTree = (AssignmentExpressionTree) tree;
	  System.out.println("=======Assignment Tree Variable======= " + assignTree.variable());
	  System.out.println("=======Assignment Tree Variable Name======= " + assignTree.variable().symbolType().name());
	  System.out.println("=======Assignment Tree Expressoin======= " + assignTree.expression().symbolType().name());
	  System.out.println("=======Assignment Tree Variable======= " + assignTree.expression().symbolType().name());
	  
	  
	  if(assignTree.variable().symbolType().isSubtypeOf("com.olf.openjvs.Table")) {
		  context.reportIssue(this, tree, "message");  
	  }
	  // TODO Auto-generated method stub
		super.visitAssignmentExpression(tree);
	}
  @Override
	public void visitLiteral(LiteralTree tree) {
	  
	  LiteralTree litTree = (LiteralTree) tree;
	  System.out.println("\n=======Literla tree=======" + litTree.value());
	  
	  
		// TODO Auto-generated method stub
		super.visitLiteral(tree);
	}
  
  @Override
	public void visitIdentifier(IdentifierTree tree) {
	  
	  IdentifierTree idtree = (IdentifierTree) tree;
	  System.out.println("\n=======indetifier Tree=======" + idtree.name());
	  
	  System.out.println("\n=======Identifier Tree usage======= " + idtree.symbol().usages());
		// TODO Auto-generated method stub
		super.visitIdentifier(tree);
	}
  
  
  
  @Override
  public void visitMethod(MethodTree tree) {
	 // System.out.println("\n Hello======================" +PrinterVisitor.print(tree));
	  
    List<AnnotationTree> annotations = tree.modifiers().annotations();
    Iterator it = tree.typeParameters().iterator();
    while(it.hasNext()) {
    	System.out.println("=======Visit Method TypeParameter=======" + it.next());
    }
    
    Symbol.MethodSymbol methodSymbol = tree.symbol();
    
    tree.simpleName().symbol().name();
    
    //for (IdentifierTree idtree : (IdentifierTree) tree.simpleName().)
    
    System.out.println("\n=======Method Symbol ========= " + methodSymbol.name());
    System.out.println("\n=======Method Symbol01 ========= " + methodSymbol.parameterTypes());
  
    
    Iterator itp = tree.typeParameters().iterator();
    
    
    while(itp.hasNext()) {
    	System.out.println("\n=======TypeParameter=======" + itp.next());
    }
    for(ModifierTree methodtree : tree.modifiers() ) {
    	
    	System.out.println("\n=======Method Tree=======" + methodtree.toString());
    }
    

    
    for (VariableTree param : tree.parameters()) {
        TypeTree typeOfParam = param.type();
      System.out.println("=======Variable Tree=======" + typeOfParam);
      System.out.println("=======Variable Tree1=======" + param.symbol().name());
      System.out.println("=======Variable Tree2=======" + param.symbol().usages());
      System.out.println("=======Variable Tree3=======" +  param.type().symbolType().name());
      param.type().symbolType().name();
    }
    //context.reportIssue( this, tree, String.format("Avoid using annotation @%s", name));
    
    for (AnnotationTree annotationTree : annotations) {
      if (annotationTree.annotationType().is(Tree.Kind.IDENTIFIER)) {
        IdentifierTree idf = (IdentifierTree) annotationTree.annotationType();
        System.out.println("\n=======Identifier Tree visit Method=== " + idf.name());
        System.out.println("\n =========== Identifier Tree 01 visit Method=== " + idf.symbol().usages());
      
/*        if (idf.name().equals(name)) {
          context.reportIssue(this, idf, String.format("Avoid using annotation @%s", name));
        }*/
      }

    }

    // The call to the super implementation allows to continue the visit of the AST.
    // Be careful to always call this method to visit every node of the tree.
    super.visitMethod(tree);
  }
}
