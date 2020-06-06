import java.beans.beancontext.BeanContextChild;

public class NBody {

    /** read the universe radius*/
    public static double readRadius(String path){
        In in = new In(path);
        int nPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** read the planets data*/
    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int nPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[nPlanets];
        for (int i=0; i<nPlanets; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet eachPlanet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            allPlanets[i] = eachPlanet;
        }
        return allPlanets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets){
            p.draw();
        }
        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T){
            // StdOut.println(time);
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i=0; i<planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);

            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
