import eu.mihosoft.vrl.v3d.*

double internalDiameter =22.15/2
double externalDiameter = 28/2
double height = 7
double chamfered = 4
int numArms =3
double z = 5
double y = 35

CSG innerCylinder = new Cylinder(internalDiameter,height).toCSG()
CSG outerCylinder = new Cylinder(externalDiameter,height,(int)50).toCSG()

CSG centerCylinder = outerCylinder.difference(innerCylinder)
Transform moveThing =new Transform().movey(y)
CSG rep = centerCylinder.movey(y)
CSG rectangleCube = new Cube(22,y,z).toCSG().toYMin().toZMin()
CSG triang = new Isosceles(6,y,y).toCSG().roty(90).toXMin().toYMin()
.movey(-rectangleCube.maxY/2)
.rotz(270)
CSG dogBone = rep.union(rectangleCube).union(centerCylinder).union(triang)
						.difference(innerCylinder.transformed(moveThing)).difference(innerCylinder);


CSG mySpinner =dogBone
for(int i=1; i<numArms;i++)
{
	mySpinner=mySpinner.union(mySpinner.rotz(360/numArms))
}
CSG text = CSG.text("Emmanuel",height-2,6).movez(1)
					.moveToCenterX()
					.movey(rectangleCube.maxY/2)
					.rotz((360/numArms)/2)
CSG newText = text.hull().toolOffset(1)
mySpinner = mySpinner.union(newText)
		.difference(text.scalez(5))
		
//mySpinner= mySpinner.difference(triang)					
//mySpinner =mySpinner.union(newText).difference(text)
mySpinner=mySpinner.setName("FigitSpinner")
return mySpinner

