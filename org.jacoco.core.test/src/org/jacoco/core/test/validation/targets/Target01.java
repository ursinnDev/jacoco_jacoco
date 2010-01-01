/*******************************************************************************
 * Copyright (c) 2009 Mountainminds GmbH & Co. KG and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *    
 * $Id: $
 *******************************************************************************/
package org.jacoco.core.test.validation.targets;

import static org.jacoco.core.test.validation.targets.Stubs.f;
import static org.jacoco.core.test.validation.targets.Stubs.i2;
import static org.jacoco.core.test.validation.targets.Stubs.nop;
import static org.jacoco.core.test.validation.targets.Stubs.t;

import java.util.Collections;

/**
 * This target exercises a set of common Java control structures.
 * 
 * @author Marc R. Hoffmann
 * @version $Revision: $
 */
public class Target01 implements Runnable {

	public void run() {

		// 1. Unconditional execution
		nop(); // $line-unconditional$

		// 2. Missed if block
		if (f()) {
			nop(); // $line-missedif$
		} else {
			nop(); // $line-executedelse$
		}

		// 3. Executed if block
		if (t()) {
			nop(); // $line-executedif$
		} else {
			nop(); // $line-missedelse$
		}

		// 4. Missed while block
		while (f()) {
			nop(); // $line-missedwhile$
		}

		// 5. Executed while block
		int i = 0;
		while (i++ < 3) {
			nop(); // $line-executedwhile$
		}

		// 6. Executed do while block
		do {
			nop(); // $line-executeddowhile$
		} while (f());

		// 7. Missed for block
		for (nop(); f(); nop()) { // $line-missedforincrementer$
			nop(); // $line-missedfor$
		}

		// 8. Executed for block
		for (int j = 0; j < 1; j++) { // $line-executedforincrementer$
			nop(); // $line-executedfor$
		}

		// 9. Missed for each block
		for (Object o : Collections.emptyList()) { // $line-missedforeachincrementer$
			nop(o); // $line-missedforeach$
		}

		// 10. Executed for each block
		for (Object o : Collections.singleton(new Object())) { // $line-executedforeachincrementer$
			nop(o); // $line-executedforeach$
		}

		// 11. Table switch with hit
		switch (i2()) {
		case 1:
			nop(); // $line-tswitch1case1$
			break;
		case 2:
			nop(); // $line-tswitch1case2$
			break;
		case 3:
			nop(); // $line-tswitch1case3$
			break;
		default:
			nop(); // $line-tswitch1default$
			break;
		}

		// 12. Continued table switch with hit
		switch (i2()) {
		case 1:
			nop(); // $line-tswitch2case1$
		case 2:
			nop(); // $line-tswitch2case2$
		case 3:
			nop(); // $line-tswitch2case3$
		default:
			nop(); // $line-tswitch2default$
		}

		// 13. Table switch without hit
		switch (i2()) {
		case 3:
			nop(); // $line-tswitch3case1$
			break;
		case 4:
			nop(); // $line-tswitch3case2$
			break;
		case 5:
			nop(); // $line-tswitch3case3$
			break;
		default:
			nop(); // $line-tswitch3default$
			break;
		}

		// 14. Lookup switch with hit
		switch (i2()) {
		case -123:
			nop(); // $line-lswitch1case1$
			break;
		case 2:
			nop(); // $line-lswitch1case2$
			break;
		case 456:
			nop(); // $line-lswitch1case3$
			break;
		default:
			nop(); // $line-lswitch1default$
			break;
		}

		// 15. Continued lookup switch with hit
		switch (i2()) {
		case -123:
			nop(); // $line-lswitch2case1$
		case 2:
			nop(); // $line-lswitch2case2$
		case 456:
			nop(); // $line-lswitch2case3$
		default:
			nop(); // $line-lswitch2default$
		}

		// 16. Lookup switch without hit
		switch (i2()) {
		case -123:
			nop(); // $line-lswitch3case1$
			break;
		case 456:
			nop(); // $line-lswitch3case2$
			break;
		case 789:
			nop(); // $line-lswitch3case3$
			break;
		default:
			nop(); // $line-lswitch3default$
			break;
		}

		// 17. Break statement
		while (true) {
			if (t()) {
				break; // $line-executedbreak$
			}
			nop(); // $line-missedafterbreak$
		}

		// 18. Continue statement
		for (int j = 0; j < 1; j++) {
			if (t()) {
				continue; // $line-executedcontinue$
			}
			nop(); // $line-missedaftercontinue$
		}

		// 19. Return statement
		if (t()) {
			return; // $line-return$
		}
		nop(); // $line-afterreturn$

	}

	public static void main(String[] args) {
		new Target01().run();
	}

}