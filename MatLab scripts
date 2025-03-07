% HOMEWORK 1 - Goran Delić 89201217

x= 5; % Creates a variable and assigns a value of 5.

u=[0,1,2,3,4,5,6]; % Creates a vector u and assigns elements.

x*u; % Multiplies the vector u by the variable x.

v=[4,5,7,8,11,14,1]; % Creates a vector v.

u.*v; % Multiplies the vectors u and v (element by element).

A=[3,4,5;2,1,6;9,12,10]; % Creates a 3x3 matrix A.



s=[-3:0.3:3]; % Creates a sequence of numbers from -3 to 3 by step 0.3.

x=[1:10]; % Creates a sequence of numbers x.
y=[10:19]; % Creates a sequence of numbers y.

scatter(x,y); % Makes a plot for x and y.


syms f(x,y);    % Creates a user-defined math function f.
f(x,y)=x^3*y;   % Assignes the equation to the function.
f(2,4);         % Solves the function(equation) for the given values.

syms x;      % Assignes a user-defined variable(value).
fplot(tan(x));  % Plots tan(x) over the default range [-5 5].


  % HOMEWORK 2 - Goran Delic 89201217
  
  
  % If the ratio H is < 1, the boat is slower than the river and we have to
  % steer our boat upstream (against the current) at an optimal angle to
  % minimize the effects of the current and travel the distance shortest to
  % the starting point.
  
  % If the ratio H is = 1, the velocities of the boat and the river are the same,
  % which means we have to point our boat to go directly across the river, at
  % a perpendicular angle to the current.
  
  % If the ratio H is > 1, the boat is faster than the river current and we
  % have to steer the boat downstream (in the direction of the current).
  
  
  % To find the optimal angle when H = 2 and to confirm what we did in lectures:
  
  % First we define x and H.
  
  syms x;
  H = 2;
  
  % Then we define the function, calculate the angle in radians and convert
  % to degrees.
  
  F = @(x) cos(x) ./ sin(x) + H ./ sin(x);
  df = diff(F,x);
  r = solve(df == 0); 
  rad = eval(r); 
  rad2deg(rad); 
   
   
   % I came to a conclusion that for H = 0.8, the optimal angle does not exist.
   
   % To calculate the distance for H = 2, we consider F = 120
   % and solve the same function as previously.


function p = BISECTION(f, a, b)
n = 1;
if (f(a)*f(b) > 0)
       disp('Wrong initial interval. No roots in this interval.')
else
    p = (a + b) / 2; % middle point
    err = abs(f(p));
    while err > 1e-4
        if f(a)*f(p) < 0
            b = p;
        else
            a = p;
        end
        p = (a + b) / 2;
        err = abs(f(p));
        n = n + 1;
        if n > 10000
            return
        end
     end
 end
end



%% Calculating velocity
syms v(t) g m k
DE = diff(v,t) == g-(k/m)*v^2;
cond = v(0) == 0;
sol(t) = dsolve(DE,cond);
sol(t)
%% Graph of velocity for three different masses.
figure
m1 = 1;% Mass 1
k0 = 1;
g0 = 10;
VT = (m1*g0/k0); % Terminal velocity when m1=1,k0=1 and g0=10
fprintf('VT(m1=1)=%6.2f' ,VT);
f = @(t)((g0^(1/2)*m1^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/m1^(1/2)))/k0^(1/2));
fplot(f, [0 30],'Color','b')

hold on

m2 = 5;% Mass 2
k0 = 1;
g0 = 10;
VT = (m2*g0/k0); % Terminal velocity when m2=5
fprintf('VT(m2=5)=%6.2f' ,VT);
f = @(t)((g0^(1/2)*m2^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/m2^(1/2)))/k0^(1/2));
fplot(f, [0 30],'Color','b')

hold on

m3 = 10;% Mass 3
k0 = 1;
g0 = 10;
VT = (m3*g0/k0); % Terminal velocity when m3=10
fprintf('VT(m3=10)=%6.2f' ,VT);
f = @(t)((g0^(1/2)*m3^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/m3^(1/2)))/k0^(1/2));
fplot(f, [0 30],'Color','b')
%% Calculating acceleration (Derivative of velocity)
acc = diff(sol, t);
acc;
%% Graph of acceleration for three different masses.
figure
ma1 = 1;% Mass 1
a = @(t) -g0*(tanh((g0^(1/2)*k0^(1/2)*t)/ma1^(1/2))^2 - 1);
fplot(a, [0 30], 'LineWidth',2,'Color','g')

hold on

ma2 = 5;% Mass 2
a = @(t) -g0*(tanh((g0^(1/2)*k0^(1/2)*t)/ma2^(1/2))^2 - 1);
fplot(a, [0 30], 'LineWidth',2,'Color','g')

hold on

ma3 = 10;% Mass 3
a = @(t) -g0*(tanh((g0^(1/2)*k0^(1/2)*t)/ma3^(1/2))^2 - 1);
fplot(a, [0 30], 'LineWidth',2,'Color','g')
%% Calculating and graphing displacement for three different masses.
figure
md1 = 1;% Mass 1
velocityF = @(g0, k0, md1, t)((g0^(1/2)*md1^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/md1^(1/2)))/k0^(1/2));
dispY = int(velocityF, t);
dispY = matlabFunction(dispY);
c0 = -dispY(g0, k0, md1, 0);
Y = @(g0, k0, md1, t)((g0^(1/2)*md1^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/md1^(1/2)))/k0^(1/2))+c0;
fplot(Y(g0, k0, md1, t), [0 30], 'Color','r')

hold on

md2 = 5;% Mass 2
velocityF = @(g0, k0, md2, t)((g0^(1/2)*md2^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/md2^(1/2)))/k0^(1/2));
dispY = int(velocityF, t);
dispY = matlabFunction(dispY);
c0 = -dispY(g0, k0, md2, 0);
Y = @(g0, k0, md2, t)((g0^(1/2)*md2^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/md2^(1/2)))/k0^(1/2))+c0;
fplot(Y(g0, k0, md2, t), [0 30], 'Color','r')

hold on

md3 = 10;% Mass 3
velocityF = @(g0, k0, md3, t)((g0^(1/2)*md3^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/md3^(1/2)))/k0^(1/2));
dispY = int(velocityF, t);
dispY = matlabFunction(dispY);
c0 = -dispY(g0, k0, md3, 0);
Y = @(g0, k0, md3, t)((g0^(1/2)*md3^(1/2)*tanh((g0^(1/2)*k0^(1/2)*t)/md3^(1/2)))/k0^(1/2))+c0;
fplot(Y(g0, k0, md3, t), [0 30], 'Color','r')


function smoothed_data = weightedSmooth(input_data)
%S A function implementing the weighted average smoothing technique

% Sometimes when we want to smooth our data, we want to emphasize certain 
% elements, and we do that by adding weights (bigger weights with more
% important elements).

% We check if input_data is a column vector
 if  ~iscolumn(input_data)
      error('Input must be a column vector.');
 end

% Getting the length of the input data
n = length(input_data);

% Initializing the smoothed data vector
smoothed_data = zeros(size(input_data));

% Defining the weights
weights = [1, 2, 5, 2, 1];

% Applying the weighted smooth starting from 3rd element
for i = 3:n-2
    smoothed_data(i) = (weights(1) * input_data(i-2) + ...
                        weights(2) * input_data(i-1) + ...
                        weights(3) * input_data(i) + ...
                        weights(4) * input_data(i+1) + ...
                        weights(5) * input_data(i+2)) / sum(weights);

end
end


%% Converting the data (read the data and convert to table)
temp = readtable('data.csv');
data = table2array(temp);

% Value 1 is time, value 2 is displacement
t = data(:,1);
y = data(:, 2);
dt = t(2) - t(1);
vy = diff(y) / dt; %vy is array of velocity
ay = diff(vy) / dt; %acceleration (derivative of velocity)

%% Unsmoothed values plotting
figure % Plotting y(displacement) and t(time)
plot(t,y,'m', LineWidth=2)
ax = gca;% Handle for x axis 
ytickformat('%.2f')
xtickformat('%.2f')
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2])% Limiting the x axis at 2.2 according to data
ylabel('Displacement', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

figure % Plotting vy(velocity) and t(time)
plot(t(1:end-1), vy,'m',LineWidth=2);
ax=gca;
ytickformat('%.2f');
xtickformat('%.2f');
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2]);
ylabel('Velocity', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

figure % Plotting ay(acceleration) and t (time)
plot(t(1:end-2), ay,'m',LineWidth=2);
ax=gca;
ytickformat('%.2f');
xtickformat('%.2f');
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2]);
ylabel('Acceleration', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

%% Smoothing the values
mywindow = 20;
setmethod = 'moving';
ys = smooth(y, mywindow, setmethod);
% then calculate the new derivatives
vy = diff(ys) / dt;
vys = diff(ys) / dt;
ays = diff(vys) / dt;

%% Plotting the smoothed values
figure % Plotting ys(smoothed displacement) and t(time)
plot(t,ys,'m', LineWidth=2)
ax = gca;% Handle for x axis 
ytickformat('%.2f')
xtickformat('%.2f')
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2])% Limiting the x axis at 2.2 according to data
ylabel('Displacement', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

figure % Plotting vys(smoothed velocity) and t(time)
plot(t(1:end-1), vys,'m',LineWidth=2);
ax=gca;
ytickformat('%.2f');
xtickformat('%.2f');
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2]);
ylabel('Velocity', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

figure % Plotting ays(smoothed acceleration) and t(time)
plot(t(1:end-2), ays,'m',LineWidth=2);
ax=gca;
ytickformat('%.2f');
xtickformat('%.2f');
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2]);
ylabel('Acceleration', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

%% I eliminated the noise from the data by implementing a technique called
%% Moving average. This technique selects a window from the whole data and
%% finds its average which then becomes the new value of the data window, then 
%% a new window is selected and so on, until the whole data is replaced with
%% new average values of the selected windows of data.
%% (The window is selected carefuly, not too big - so we dont oversmooth the data,
%% and not too small - so we dont undersmooth the data).

%% Smoothing with weighted moving average
yws = weightedSmooth(y);
% Calculating new derivatives
vws = diff(yws) / dt;
aws = diff(vws) / dt;

%% Plotting the weighted-smoothed values
figure % Plotting yws(weighted-smoothed displacement) and t(time)
plot(t,yws,'m', LineWidth=2)
ax = gca;% Handle for x axis 
ytickformat('%.2f')
xtickformat('%.2f')
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2])% Limiting the x axis at 2.2 according to data
ylabel('Displacement', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

figure % Plotting vws(weighted-smoothed velocity) and t(time)
plot(t(1:end-1), vws,'m',LineWidth=2);
ax=gca;
ytickformat('%.2f');
xtickformat('%.2f');
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2]);
ylabel('Velocity', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)

figure % Plotting aws(weighted-smoothed acceleration) and t(time)
plot(t(1:end-2), aws,'m',LineWidth=2);
ax=gca;
ytickformat('%.2f');
xtickformat('%.2f');
ax.XAxis.FontSize = 16;
ax.YAxis.FontSize = 16;
xlim ([0 2.2]);
ylabel('Acceleration', 'FontSize',18)
xlabel('Time(seconds)', 'FontSize',18)


% This is for the first part : "How high must h be so that the ski jumper
% can cover the maximum distance d?"
syms h;
H=20;

D=@(h)(2*sqrt(h.*(H-h)));
df=diff(D,h);
s1=solve(df == 0)
Dmax= D(s1)

%% Plotting
figure
fplot(D,[-2 22], 'LineWidth',2);
ylim([0 22]);
ax=gca;
ax.XAxis.FontSize=16;
ax.YAxis.FontSize=16;
xlabel('h','FontSize',20);
ylabel('Length(jump)','FontSize',20);
saveas(gcf,'SKIJUMP2.jpg')

%% Second part - ratio
syms r;
Dr=@(r)(2*sqrt(r.*(1-r)));

drf=diff(Dr,r)
s2=solve(drf == 0)
Drmax= Dr(s2)

D2 = Dr(s2)/2 %DISTANCE
rD2 = solve(Dr == D2)
eval(rD2) % We get two solutions

%% We conclude that h is directly connected to Dmax and the distance of the jump 
%% depends on the time that the jumper spends in air and his velocity. As h is bigger, he spends more time
%% in the air with less velocity, therefore he travels shorter distance.
%% On the contrary, if h is smaller, the jumper spends less time in the air with bigger 
%% velocity so he travels further.


%% Reading the points from the graph and extracting coefficients
x = [-10; 0; 10; 20; 30; 40; 45];
y = [0; 7; 10; 15; 30; 50; 70];
[f fo] = fit(x, y, 'poly3'); % using the fit function
coeffvalues(f) % extracting coefficients

%% Calculating Relative humidity, Absolute vapor density and temperature based on inputs
% Example 1: Calculate relative humidity
AVD = 10; % absolute vapor density in g/m^3
T = 25;   % temperature in degrees Celsius
[RH, ~, ~] = calculateHumidityVariables(AVD, T, 0, 1); % Calling the function
disp(['Relative Humidity: ', num2str(RH), '%']);

% Example 2: Calculate absolute vapor density
RH = 70;  % relative humidity in percentage
T = 25;   % temperature in degrees Celsius
[~, AVD, ~] = calculateHumidityVariables(RH, T, 0, 2); % Calling the function
disp(['Absolute Vapor Density: ', num2str(AVD), ' g/m^3']);

% Example 3: Calculate temperature
AVD = 10; % absolute vapor density in g/m^3
RH = 70;  % relative humidity in percentage
[~, ~, T] = calculateHumidityVariables(AVD, RH, 0, 3); % Calling the function
disp(['Temperature: ', num2str(T), ' °C']);


function [RH, AVD, T] = calculateHumidityVariables(input1, input2, input3, option)
    % Constants
    a = 17.27;
    b = 237.7;
    
    switch option
        case 1 % Calculate relative humidity
            AVD = input1;
            T = input2;

            % Calculate saturation vapor pressure (es) and actual vapor pressure (e)
            es = 6.112 * exp((a * T) / (b + T));
            e = AVD * 461.5 * T / 1000;

            % Calculate relative humidity
            RH = (e / es) * 100;

        case 2 % Calculate absolute vapor density
            RH = input1;
            T = input2;

            % Calculate saturation vapor pressure (es)
            es = 6.112 * exp((a * T) / (b + T));

            % Calculate actual vapor pressure (e)
            e = RH / 100 * es;

            % Calculate absolute vapor density
            AVD = e * 1000 / (461.5 * T);

        case 3 % Calculate temperature
            AVD = input1;
            RH = input2;

            % Initial guess for temperature
            T = 20;

            % Solve for temperature using the Newton-Raphson method
            for i = 1:100
                es = 6.112 * exp((a * T) / (b + T));
                e = RH / 100 * es;
                f = e - AVD * 461.5 * T / 1000;

                % Derivative of the function
                df = (a * b * es) / ((b + T)^2);

                % Update temperature
                T = T - f / df;

                % Check for convergence
                if abs(f) < 1e-6
                    break;
                end
            end
    end
end


%% IDEA AND EXPLANATION
% I just added to my x coordinate a -rand and to my y coordinate a +rand in
% order to get the desired southeast wind direction. For the stronger and weaker
% wind, I had an idea of using a "weighted" wind, but could not realize it
% sadly. 
%%
% define number of steps and number of particles
Nstep=100; % number of steps
Np=100; % number of particles

strongWind = rand(1:3);
weakWind = rand(3:7);
%
cMax=0; % save abs max coordinate for x and y limits / plot
lastPxy=zeros(Np,2); % save last coordinates
Z=NaN(Nstep*Np,1); % all step lengths >> to calculate average step length "l", for r=sqrt(N)l
%
figure

hold on
%%
Pxy = zeros(Nstep, 2);
for p=1:Np
    x=0;
    y=0;
    for i=1:Nstep
        dx = 2*rand -1;
        dy = 2*rand -1;
        x = x + dx - rand;
        y = y + dy + rand;
        Pxy(i, 1) = x;
        Pxy(i, 2) = y;
        Z((i + (p-1)*Nstep), 1) = sqrt(dx^2 + dy^2);
    end
    temp = max(abs(Pxy), [], "all");
    if temp > cMax
        cMax = temp;
    end
    plot(Pxy(:, 1), Pxy(:, 2), 'Color', [0.5 0.5 0.5]);
    drawnow
    lastPxy(p, :) = Pxy(Nstep, :); 

end
saveas(gcf,'windfigure1.jpg')
%%
scatter(0, 0, 'red', 'filled')
scatter(lastPxy(:, 1), lastPxy(:, 2),  'blue', 'filled')
xlim([-cMax*1.1 cMax*1.1])
ylim([-cMax*1.1 cMax*1.1])
%%
% r is the average distance between the end and start positions of the particle
% N is the number of steps, and 
% l is the average step length.
r = mean(sqrt(sum(lastPxy.^2, 2)));
l = mean(Z);
fprintf('sqrt(N)*l is: %8.3f \n', sqrt(Nstep)*l);
fprintf('r is: %8.4f\n', r);

  

%% IDEA AND EXPLANATION
% I just added to my x coordinate a -rand and to my y coordinate a +rand in
% order to get the desired southeast wind direction.
%% In the improved version of the code, wind direction and strength are inserted 
%% by the user.

%% User input for wind direction and strength
wind_direction = input('Enter wind direction in degrees (0 being North): ');
wind_strength = input('Enter wind strength (positive scalar value): ');

% Convert wind direction to radians
wind_angle = deg2rad(mod(-wind_direction + 90, 360)); % Adjusting to match MATLAB's coordinate system

% define number of steps and number of particles
Nstep = 100; % number of steps
Np = 100; % number of particles

D = 0.7;
%
cMax = 0; % save abs max coordinate for x and y limits / plot
lastPxy = zeros(Np, 2); % save last coordinates
Z = NaN(Nstep * Np, 1); % all step lengths >> to calculate average step length "l", for r=sqrt(N)l
%
figure

hold on
%%
Pxy = zeros(Nstep, 2);
for p = 1:Np
    x = 0;
    y = 0;
    for i = 1:Nstep
        dx = 2 * rand - 1;
        dy = 2 * rand - 1;
        % Modify the following lines to include wind direction and strength
        x = x + dx - rand + wind_strength * cos(wind_angle);
        y = y + dy + rand + wind_strength * sin(wind_angle);
        Pxy(i, 1) = x;
        Pxy(i, 2) = y;
        Z((i + (p - 1) * Nstep), 1) = sqrt(dx^2 + dy^2);
    end
    temp = max(abs(Pxy), [], "all");
    if temp > cMax
        cMax = temp;
    end
    plot(Pxy(:, 1), Pxy(:, 2), 'Color', [0.5 0.5 0.5]);
    drawnow
    lastPxy(p, :) = Pxy(Nstep, :);

end
saveas(gcf, 'windfigure1.jpg')
%%
scatter(0, 0, 'red', 'filled')
scatter(lastPxy(:, 1), lastPxy(:, 2), 'blue', 'filled')
xlim([-cMax * 1.1, cMax * 1.1])
ylim([-cMax * 1.1, cMax * 1.1])
%%
% r is the average distance between the end and start positions of the particle
% N is the number of steps, and
% l is the average step length.
r = mean(sqrt(sum(lastPxy.^2, 2)));
l = mean(Z);
fprintf('sqrt(N)*l is: %8.3f \n', sqrt(Nstep) * l);
fprintf('r is: %8.4f\n', r);


%% Same for one charge and more charges
k = 8.99 * 10^9;
e0 = 1.6E-19; % charge of an electron
g = -1:0.01:1;
[xg, yg] = meshgrid(g);

% Electric charge that should be an integral multiple of the charge of an electron
q1 = -1 * e0; % just change to 1 for proton, -1 to electron

% Coordinates of the charge - USE TO SPECIFY POSITION OF MOLECULES
xp1 = 0;
yp1 = 0;

xi1 = xg - xp1;
yi1 = yg - yp1;

R1 = sqrt(xi1.^2 + yi1.^2); % distance
V1 = q1 * k ./ R1; 
d = 0.1;
Vmax1 = k * q1 / d;
V1(R1 < d) = Vmax1;

% Charge 2
q2 = -1 * e0; % just change to 1 for proton, -1 to electron

% Coordinates of the charge - USE TO SPECIFY POSITION OF MOLECULES
xp2 = 0.4;
yp2 = 0.55;

xi2 = xg - xp2;
yi2 = yg - yp2;

R2 = sqrt(xi2.^2 + yi2.^2); % distance
V2 = q2 * k ./ R2; 
d = 0.1;
Vmax2 = k * q2 / d;
V2(R2 < d) = Vmax2;

% Additional charges
q3 = -0.5 * e0;
q4 = -0.5 * e0;
q5 = -0.5 * e0;
q6 = -0.5 * e0;

% Coordinates of additional charges
xp3 = 0.6;
yp3 = 0.3;

xp4 = -0.2;
yp4 = -0.2;

xp5 = 0.2;
yp5 = 0.3;

xp6 = 0.2;
yp6 = -0.2;

xi3 = xg - xp3;
yi3 = yg - yp3;

xi4 = xg - xp4;
yi4 = yg - yp4;

xi5 = xg - xp5;
yi5 = yg - yp5;

xi6 = xg - xp6;
yi6 = yg - yp6;

R3 = sqrt(xi3.^2 + yi3.^2);
R4 = sqrt(xi4.^2 + yi4.^2);
R5 = sqrt(xi5.^2 + yi5.^2);
R6 = sqrt(xi6.^2 + yi6.^2);

V3 = q3 * k ./ R3;
V4 = q4 * k ./ R4;
V5 = q5 * k ./ R5;
V6 = q6 * k ./ R6;

V3(R3 < d) = -k * q3 / d;
V4(R4 < d) = -k * q4 / d;
V5(R5 < d) = -k * q5 / d;
V6(R6 < d) = -k * q6 / d;

% Combine potentials
v = V1 + V2 + V3 + V4 + V5 + V6;

% Plotting
figure
contourf(xg, yg, v, 100, ':', 'LineWidth', 1);
colormap("jet");
hold on;
text(xp1, yp1, '-', 'FontSize', 20, 'Color', 'w', 'HorizontalAlignment', 'center', 'VerticalAlignment', 'middle');
text(xp2, yp2, '-', 'FontSize', 20, 'Color', 'w', 'HorizontalAlignment', 'center', 'VerticalAlignment', 'middle');
text(xp3, yp3, '+', 'FontSize', 15, 'Color', 'b', 'HorizontalAlignment', 'center', 'VerticalAlignment', 'middle');
text(xp4, yp4, '+', 'FontSize', 15, 'Color', 'b', 'HorizontalAlignment', 'center', 'VerticalAlignment', 'middle');
text(xp5, yp5, '+', 'FontSize', 15, 'Color', 'b', 'HorizontalAlignment', 'center', 'VerticalAlignment', 'middle');
text(xp6, yp6, '+', 'FontSize', 15, 'Color', 'b', 'HorizontalAlignment', 'center', 'VerticalAlignment', 'middle');

plot([xp5, xp1], [yp5, yp1], 'k-', 'LineWidth', 3);

ax = gca;
axis(ax, 'equal');



