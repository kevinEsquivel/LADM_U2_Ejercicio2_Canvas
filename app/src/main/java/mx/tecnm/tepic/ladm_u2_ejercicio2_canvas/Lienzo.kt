package mx.tecnm.tepic.ladm_u2_ejercicio2_canvas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View

class Lienzo(p:MainActivity) :View(p){
    var yTouch = 750f
    var xTouch = 1300f

    var xC=270f
    var yC=450f
    var incrementroX =5
    var xR = 200f
    var yR = 1000f

    var trabajando =false

    var tocoImagen =false
    var amazon = BitmapFactory.decodeResource(resources, R.drawable.icono4)

    val timer= object :CountDownTimer(2000,100){
        override fun onTick(p0: Long) {
            xC += incrementroX
            if(xC<=-200||xC>=1200){
                incrementroX *=-1
            }
            invalidate()
        }

        override fun onFinish() {
            start()
        }
    }

    val timer2= object :CountDownTimer(2000,100){
        override fun onTick(p0: Long) {
            yR += incrementroX
            if(yR<=-200||yR>=1200){
                incrementroX *=-1
            }
            invalidate()
        }

        override fun onFinish() {
            start()
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()
        //SE pinta el fondo del canvas de Verde olivo
        c.drawARGB(255, 178, 239, 135)
        //Se dibuja rectangulo mitad hacia abajo y derecha
        p.setColor(Color.MAGENTA)
        c.drawRect(540f, 912f, 1080f, 1820f, p)
        //Se dibuja rectangulo mitad hacia arriba y derecha
        p.style = Paint.Style.STROKE
        p.strokeWidth = 15f
        p.setColor(Color.DKGRAY)
        c.drawRect(540f, 0f, 1080f, 912f, p)

        p.style = Paint.Style.FILL
        p.textSize = 60f
        p.setColor(Color.RED)
        c.drawText("Kevin Esuijvel", 100f, 100f, p)

        p.setColor(Color.BLUE)
        c.drawText("Ancho" + width + "Alto" + height, 100f, 200f, p)
        c.drawText("Xtouch" + xTouch.toInt() + "Ytouch" + yTouch.toInt(), 100f, 300f, p)

        //circulo
        p.setColor(Color.YELLOW)
        c.drawCircle(xC, yC, 100f, p)
        p.style = Paint.Style.STROKE
        p.setColor(Color.BLACK)
        c.drawCircle(xC, yC, 100f, p)

        //Cuadrado
        p.style = Paint.Style.FILL
        p.setColor(Color.CYAN)
        c.drawRect(xR, yR, 400f, 1200f, p)
        p.style = Paint.Style.STROKE
        p.setColor(Color.BLUE)
        c.drawRect(xR, yR, 400f, 1200f, p)


        c.drawBitmap(amazon, (xTouch-amazon.width/2), (yTouch-amazon.height/2), p)
    }
       override fun onTouchEvent(event: MotionEvent): Boolean {
            //event.action=Presione,arrastro, libero
            //event.x event.y
            if (event.action == MotionEvent.ACTION_DOWN) {
                //Entra si presiono
                if(trabajando==false){
                    timer.start()
                    trabajando=true
                }
                var x2 = xTouch+amazon.width
                var y2 = xTouch+amazon.height
                tocoImagen=false
                if (event.x >= xTouch && event.x <= x2){
                    if (event.y>= yTouch && event.y <= y2){
                        tocoImagen=true
                    }
                }

            }
            if (event.action == MotionEvent.ACTION_MOVE) {
                //Entra si arrastro
                if(tocoImagen==true) {
                    xTouch = event.x
                    yTouch = event.y
                }
            }
            if (event.action == MotionEvent.ACTION_UP) {
                //Entra si libero
            }
           invalidate()//Vuelve a llamar el onDraw y por ende repinta todo
            return true
        }
    }

