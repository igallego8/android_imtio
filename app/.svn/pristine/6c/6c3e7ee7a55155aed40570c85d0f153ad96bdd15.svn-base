package com.agora.main.drawer;

public class DrawerItem {

	private int name;
	private int text2;
	private String imageName;
	private int image;
	private int image2;
	private int color;
	private int icon;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getImage2() {
		return image2;
	}

	public void setImage2(int image2) {
		this.image2 = image2;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getText2() {
		return text2;
	}

	public void setText2(int text2) {
		this.text2 = text2;
	}



	public DrawerItem(int name, int image, int image2, int icon, int text2) {
		this.icon = icon;
		this.name = name;
		this.image2=image2;
		this.image=image;
		this.text2=text2;
	}


	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getImage() {
		return image;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;

	}


	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof DrawerItem)) {
			return false;
		}
		DrawerItem aux = (DrawerItem) o;
		if (this.getName()==(aux.getName())) {
			return true;
		}
		return false;

	}

}
