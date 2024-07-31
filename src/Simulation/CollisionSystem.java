package Simulation;

import edu.princeton.cs.algs4.MinPQ;

public class CollisionSystem
{
    private class Event implements Comparable<Event>
    {
        private double time; // time of event
        private Particle a, b; // particles involved in event
        private int countA, countB; // collision counts for a and b
        public Event(double t, Particle a, Particle b) { }
        public int compareTo(Event that)
        {
            return (int) (this.time - that.time);
        }
        public boolean isValid()
        {

        }
    }

    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particles
    public CollisionSystem(Particle[] particles)
    {

    }

    private void predict(Particle a)
    {
        if (a == null) return;
        for (int i = 0; i < N; i++)
        {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }
        pq.insert(new Event(t + a.timeToHitVerticalWall() , a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }
    private void redraw() { }

    public void simulate()
    {
        pq = new MinPQ<Event>();
        for(int i = 0; i < N; i++) predict(particles[i]);
        pq.insert(new Event(0, null, null));
        while(!pq.isEmpty())
        {
            Event event = pq.delMin();
            if(!event.isValid()) continue;
            Particle a = event.a;
            Particle b = event.b;
            for(int i = 0; i < N; i++)
                particles[i].move(event.time - t);
            t = event.time;
            if (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) a.bounceOffVerticalWall();
            else if (a == null && b != null) b.bounceOffHorizontalWall();
            else if (a == null && b == null) redraw();
            predict(a);
            predict(b);
        }
    }
}
