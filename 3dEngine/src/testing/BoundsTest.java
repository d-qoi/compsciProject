package testing;

import core.Bounds3D;

public class BoundsTest {


	public static void main(String[] args) {
		
		System.out.println("Test ## : <expected> => <test>");
		
		Bounds3D objA, objB;
		
		objA = new Bounds3D(0,0,0,3,3,3);
		objB = new Bounds3D(0,0,6,3,3,3);
		
		
		System.out.println("Intersection Test 01 : false => " + objA.intersects(objB));
		
		objB = new Bounds3D(0,0,0,3,3,3); //TODO Fix intersection code, it is now tested and broken
		
		System.out.println("Intersection Test 02 : true => " + objA.intersects(objB));
		

	}

}
