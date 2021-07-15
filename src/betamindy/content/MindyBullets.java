package betamindy.content;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import betamindy.entities.bullet.*;
import betamindy.graphics.*;
import mindustry.content.*;
import mindustry.ctype.ContentList;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.Vars.world;

public class MindyBullets implements ContentList {
    public static BulletType payBullet, payBulletBig, homingPay, homingPayBig, glassPiece, glassPieceBig, bigStar, smallStar, biggerStar, colorFireball, icyZone, icyZoneSmall;
    @Override
    public void load(){
        payBullet = new PayloadBullet(1.6f){{
            hitEffect = Fx.mineBig;
            despawnEffect = Fx.none;
            hitColor = Pal.engine;

            lifetime = 80f;
            trailSize = 6f;
            splashDamageRadius = 30f;
        }};

        payBulletBig = new PayloadBullet(3.2f){{
            hitEffect = Fx.mineHuge;
            despawnEffect = MindyFx.payShock;
            hitColor = Pal.lancerLaser;

            lifetime = 20f;
            trailSize = 8f;
            splashDamageRadius = 50f;
            hitShake = 2.5f;
            trailEffect = MindyFx.hyperTrail;
        }};

        homingPay = new HomingPayloadBullet(1.6f){{
            hitEffect = Fx.mineBig;
            despawnEffect = Fx.none;
            hitColor = Pal.engine;

            lifetime = 80f;
            trailSize = 6f;
            splashDamageRadius = 30f;

            homingPower = 0.03f;
            homingRange = 120f;
        }};

        homingPayBig = new HomingPayloadBullet(3.2f){{
            hitEffect = Fx.mineHuge;
            despawnEffect = MindyFx.payShock;
            hitColor = Pal.lancerLaser;

            lifetime = 20f;
            trailSize = 8f;
            splashDamageRadius = 50f;
            hitShake = 2.5f;
            trailEffect = MindyFx.hyperTrail;

            homingPower = 0.01f;
            homingRange = 120f;
        }};

        glassPiece = new GlassBulletType(4f, 30f, "betamindy-glass"){{
            trailColor = Color.white;
            trailParam = 0.8f;
            trailChance = 0.04f;
            lifetime = 45f;
            hitEffect = Fx.none;
            width = 6f; height = 6f;

            despawnEffect = Fx.none;
        }};

        glassPieceBig = new GlassBulletType(5f, 65f, "betamindy-glassbig"){{
            trailColor = Color.white;
            trailParam = 1.8f;
            trailChance = 0.04f;
            lifetime = 50f;
            hitEffect = Fx.none;
            width = 8f; height = 8f;

            despawnEffect = Fx.none;
        }};

        smallStar = new BasicBulletType(3f, 60f, "betamindy-starsmall"){{
            frontColor = Color.white;
            backColor = Color.white;
            pierce = true;
            pierceCap = 10;
            hitEffect = Fx.none;
            despawnEffect = Fx.mineBig;
            lifetime = 80f;
            width = 16f;
            height = 16f;
            spin = 0.05f;
        }};

        bigStar = new FallingStar(2f, 360f){{
            splashDamageRadius = 60f;
            splashDamage = 360f;
            inaccuracy = 26f;
            fragBullet = smallStar;
            fragBullets = 7;

            trailEffect = Fx.none;
            hitShake = 3f;
            size = 250f;
            fallTime = 270f;
        }};

        biggerStar = new FallingStar(1.7f, 360f){{
            splashDamageRadius = 90f;
            splashDamage = 500f;
            inaccuracy = 20f;
            fragBullet = smallStar;
            fragBullets = 15;
            fragShots = 3;

            trailEffect = Fx.none;
            hitShake = 4f;
            size = 400f;
            fallTime = 650f;

            shiny = true;
        }};

        colorFireball = new BulletType(1f, 4){
            {
                pierce = true;
                collidesTiles = false;
                collides = false;
                drag = 0.03f;
                hitEffect = despawnEffect = Fx.none;
            }

            @Override
            public void init(Bullet b){
                b.vel.setLength(0.6f + Mathf.random(2f));
                if(!(b.data instanceof Item)) b.data(Items.coal);
            }

            @Override
            public void draw(Bullet b){
                FireColor.fset((Item)b.data, b.fin(), Color.gray);
                Fill.circle(b.x, b.y, 3f * b.fout());
                Draw.reset();
            }

            @Override
            public void update(Bullet b){
                if(Mathf.chance(0.04 * Time.delta)){
                    Tile tile = world.tileWorld(b.x, b.y);
                    if(tile != null){
                        Fires.create(tile);
                    }
                }

                if(Mathf.chance(0.1 * Time.delta)){
                    Fx.fireballsmoke.at(b.x, b.y);
                }

                if(Mathf.chance(0.1 * Time.delta)){
                    MindyFx.ballfire.at(b.x, b.y, 0f, b.data);
                }
            }
        };

        icyZone = new StatusBulletType(MindyStatusEffects.icy, 65f){{
            lifetime = 600f;
        }};

        icyZoneSmall = new StatusBulletType(MindyStatusEffects.icy, 25f){{
            lifetime = 300f;
        }};
    }
}
