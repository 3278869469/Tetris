package model;

import java.awt.*;

public class blocks {
	public Point[] points;

	blocks(int[] xs, int[] ys) {
		points = new Point[4];
		for (int i = 0; i < 4; i++) {
			points[i] = new Point(xs[i], ys[i]);
		}
	}

	blocks(blocks block) {
		points = new Point[4];
		for (int i = 0; i < 4; i++) {
			points[i] = new Point(block.points[i].x, block.points[i].y);
		}
	}
}
