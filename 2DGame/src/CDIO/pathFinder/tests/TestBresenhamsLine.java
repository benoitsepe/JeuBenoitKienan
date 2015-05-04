package CDIO.pathFinder.tests;

import java.awt.Point;
import java.util.ArrayList;

import CDIO.pathFinder.AreaMap;
import CDIO.pathFinder.bresenhamsLine.BresenhamsLine;
import CDIO.pathFinder.graphics.PrintMap;
import CDIO.pathFinder.utils.Logger;
import CDIO.pathFinder.utils.StopWatch;

public class TestBresenhamsLine {

    private static int mapWith = 50;
    private static int mapHeight = 50;

    public static void main(String[] args) {
	Point a = new Point(49, 49);
	Point b = new Point(0, 0);

	Logger log = new Logger();
	StopWatch s = new StopWatch();

	log.addToLog("Initializing " + mapWith + "x" + mapHeight + " map...");
	AreaMap map = new AreaMap(mapWith, mapHeight);

	log.addToLog("Generating Bresenham's Line from " + a.x + "," + a.y + " to " + b.x + "," + b.y + "...");
	s.start();
	ArrayList<Point> line = BresenhamsLine.getPointsOnLine(a, b);
	s.stop();
	log.addToLog("Generation took " + s.getElapsedTime() + " ms");

	String str = "";
	for (Point point : line) {
	    str = str + "(" + point.x + "," + point.y + ") ";
	}
	log.addToLog("Line is:" + str);

	log.addToLog("Writing line to map...");
	for (Point point : line) {
	    map.setObstacle(point.x, point.y, true);
	}
	log.addToLog("Printing map...");
	new PrintMap(map, new ArrayList<Point>());

    }

}
