class saw(object);
img=[pygame.image.load(os.path.join('images','SAWO.png')),pygame.image.load(os.path.join('images','SAWO.png')), pygame.image.io]
def_init_(self,x,y,width,height);
self.x=x;
self.y=y;
self.width=width;
self.height=height;
self.hitbox=(x,y,width,height);
self.count=0;

def draw(self, win):
self.hitbox=(self.x +5,self.width-10,self.height);
if self.count >=8:
self.count =0;
win.blit(self.img[self.count/2], (self.x,self.y))
self.count+=1;
pygame.draw.rect(win,(255,0,0),self.hitbox,2)
