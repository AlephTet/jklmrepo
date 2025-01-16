

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class EffectManager
{

	private List<Effect> effects = new ArrayList<Effect>();
	
	private static EffectManager eManager;
	public static EffectManager getEffectManager() 
	{
		if (eManager == null) 
			eManager = new EffectManager();
		return eManager;
	}
	public static void resetEffectManager() {
		eManager = null;
	}
	public void addEffect(Effect e) 
	{
		effects.add(e);
	}
	public void tick(long delta) 
	{
		for (Effect e : effects)
			e.tick(delta);
		
		Iterator<Effect> iterator = effects.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().done())
				iterator.remove();
		}
	}
	public void paintEffects(Graphics2D g) {
		for (Effect e : effects)
			e.paint(g);
	}
	public static interface Effect 
	{
		
		public void tick(long delta);
		public void paint(Graphics2D g);
		public boolean done();
		
	}
}
