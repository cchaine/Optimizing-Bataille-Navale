package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.omg.CORBA.PUBLIC_MEMBER;

import core.BoatType;
import utils.AssetLoader;

public class BoatButton {

	Rectangle2D bounds;
	Rectangle2D textBound;
	
	boolean used = false;
	boolean active = false;
	boolean hovered = false;
	
	BoatType type;

	public BoatButton(int x, int y, BoatType type) {
		bounds = new Rectangle(x, y, 44 * type.getSize(), 40);
		
		this.type = type;
	}

	public boolean contains(int mouseX, int mouseY) {
		if(textBound != null)
			return bounds.contains(mouseX, mouseY) || textBound.contains(mouseX, mouseY);
		else 
			return false;
	}
	
	public void update(int mouseX, int mouseY)
	{
		//On verifie si la position de la souris est dans le bouton
		if (bounds.contains(mouseX, mouseY) && !used && !active)
			hovered = true;
		else
			hovered = false;
	}

	public void render(Graphics g) {
		if (used) {
			g.setColor(new Color(150, 185, 252, 255));
		} else if (active) {
			g.setColor(new Color(68, 92, 179, 255));
		}else if (hovered){
			g.setColor(new Color(88, 124, 175));
		}else {
			g.setColor(new Color(0, 35, 102, 255));
		}
		g.fillRect((int)bounds.getX(), (int)(bounds.getY() - bounds.getHeight() /2) , (int)bounds.getWidth(), (int)bounds.getHeight());
		
		g.setFont(AssetLoader.helvetica25);
		if(textBound == null)
			textBound = new Rectangle((int) (bounds.getX() - g.getFontMetrics().getStringBounds(type.toString(), g).getWidth() - 10), 
					(int)(bounds.getY() - bounds.getHeight() /2 + 28), (int)g.getFontMetrics().getStringBounds(type.toString(), g).getWidth() + 10, 
					(int)bounds.getHeight());
		g.drawString(type.toString(), (int)textBound.getX(), (int)textBound.getY());
	}
	
	public void mouseReleased(int mouseX, int mouseY)
	{
		if (bounds.contains(mouseX, mouseY) && !used)
			active = true;
		else
			active = false;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	
	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public void setPosition(int x, int y)
	{
		bounds.setRect(x - bounds.getWidth() / 2, y - bounds.getHeight() / 2, bounds.getWidth(), bounds.getHeight());
	}
	
	public Rectangle2D getBounds()
	{
		return bounds;
	}
}
